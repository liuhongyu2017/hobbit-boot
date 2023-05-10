package org.hobbit.gateway.dynamic;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hobbit.core.launch.constant.NacosConstant;
import org.hobbit.core.launch.props.HobbitProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 动态路由监听器
 *
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Order
@Slf4j
@Component
public class DynamicRouteServiceListener {

  private final DynamicRouteService dynamicRouteService;
  private final NacosDiscoveryProperties nacosDiscoveryProperties;
  private final NacosConfigProperties nacosConfigProperties;
  private final HobbitProperties hobbitProperties;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public DynamicRouteServiceListener(DynamicRouteService dynamicRouteService,
      NacosDiscoveryProperties nacosDiscoveryProperties,
      NacosConfigProperties nacosConfigProperties, HobbitProperties hobbitProperties) {
    this.dynamicRouteService = dynamicRouteService;
    this.nacosDiscoveryProperties = nacosDiscoveryProperties;
    this.nacosConfigProperties = nacosConfigProperties;
    this.hobbitProperties = hobbitProperties;
    dynamicRouteServiceListener();
  }

  /**
   * 监听Nacos下发的动态路由配置
   */
  @SneakyThrows
  private void dynamicRouteServiceListener() {
    try {
      String dataId = NacosConstant.dataId(hobbitProperties.getName(), hobbitProperties.getEnv(),
          NacosConstant.NACOS_CONFIG_JSON_FORMAT);
      String group = nacosConfigProperties.getGroup();
      Properties properties = new Properties();
      properties.setProperty(PropertyKeyConst.SERVER_ADDR,
          nacosDiscoveryProperties.getServerAddr());
      properties.setProperty(PropertyKeyConst.NAMESPACE, nacosDiscoveryProperties.getNamespace());
      ConfigService configService = NacosFactory.createConfigService(properties);
      configService.addListener(dataId, group, new Listener() {
        @SneakyThrows
        @Override
        public void receiveConfigInfo(String configInfo) {
          List<RouteDefinition> routeDefinitions = objectMapper.readValue(configInfo,
              new TypeReference<>() {
              });
          dynamicRouteService.updateList(routeDefinitions);
        }

        @Override
        public Executor getExecutor() {
          return null;
        }
      });
      String configInfo = configService.getConfig(dataId, group, 5000);
      if (configInfo != null) {
        List<RouteDefinition> routeDefinitions = objectMapper.readValue(configInfo,
            new TypeReference<>() {
            });
        dynamicRouteService.updateList(routeDefinitions);
      }
    } catch (NacosException e) {
      e.printStackTrace();
    }
  }
}
