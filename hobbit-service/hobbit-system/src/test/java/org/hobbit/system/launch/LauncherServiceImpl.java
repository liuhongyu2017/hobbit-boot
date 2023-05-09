package org.hobbit.system.launch;

import java.util.Properties;
import net.dreamlu.mica.auto.annotation.AutoService;
import org.hobbit.common.constant.LauncherConstant;
import org.hobbit.core.launch.service.LauncherService;
import org.hobbit.core.launch.utils.PropsUtil;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
@AutoService(LauncherService.class)
public class LauncherServiceImpl implements LauncherService {

  @Override
  public void launcher(SpringApplicationBuilder builder, String appName, String profile,
      boolean isLocalDev) {
    Properties props = System.getProperties();
    // 通用注册
    PropsUtil.setProperty(props,
        "spring.cloud.nacos.discovery.server-addr", LauncherConstant.nacosAddr(profile));
//    PropsUtil.setProperty(props,
//        "spring.cloud.nacos.discovery.namespace", LauncherConstant.nacosNamespace(profile));
    PropsUtil.setProperty(props,
        "spring.cloud.nacos.config.server-addr", LauncherConstant.nacosAddr(profile));
//    PropsUtil.setProperty(props,
//        "spring.cloud.nacos.config.namespace", LauncherConstant.nacosNamespace(profile));
  }
}
