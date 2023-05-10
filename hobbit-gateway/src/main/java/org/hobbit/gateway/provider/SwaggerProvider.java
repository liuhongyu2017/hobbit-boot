package org.hobbit.gateway.provider;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.hobbit.gateway.props.RouteProperties;
import org.hobbit.gateway.props.RouteResource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * 聚合接口文档注册
 *
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Primary
@Component
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {

  private static final String API_URI = "/v2/api-docs";
  private final RouteProperties routeProperties;

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();
    List<RouteResource> routeResources = routeProperties.getResources();
    routeResources.forEach(routeResource -> resources.add(swaggerResource(routeResource)));
    return resources;
  }

  private SwaggerResource swaggerResource(RouteResource routeResource) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(routeResource.getName());
    swaggerResource.setLocation(routeResource.getLocation().concat(API_URI));
    swaggerResource.setSwaggerVersion(routeResource.getVersion());
    return swaggerResource;
  }
}
