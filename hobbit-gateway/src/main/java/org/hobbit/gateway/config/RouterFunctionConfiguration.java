package org.hobbit.gateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hobbit.gateway.handler.SwaggerResourceHandler;
import org.hobbit.gateway.handler.SwaggerSecurityHandler;
import org.hobbit.gateway.handler.SwaggerUiHandler;
import org.hobbit.gateway.props.AuthProperties;
import org.hobbit.gateway.props.RouteProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({RouteProperties.class, AuthProperties.class})
public class RouterFunctionConfiguration {

  private final SwaggerResourceHandler swaggerResourceHandler;
  private final SwaggerSecurityHandler swaggerSecurityHandler;
  private final SwaggerUiHandler swaggerUiHandler;

  /**
   * 这里为支持的请求头，如果有自定义的header字段请自己添加
   */
  private static final String ALLOWED_HEADERS = "X-Requested-With, Tenant-Id, Rapid-Auth, Content-Type, Authorization, credential, X-XSRF-TOKEN, token, username, client";
  private static final String ALLOWED_METHODS = "GET,POST,PUT,DELETE,OPTIONS,HEAD";
  private static final String ALLOWED_ORIGIN = "*";
  private static final String ALLOWED_EXPOSE = "*";
  private static final String MAX_AGE = "18000L";

  /**
   * 跨域配置
   */
  @Bean
  public WebFilter corsFilter() {
    return (ServerWebExchange ctx, WebFilterChain chain) -> {
      ServerHttpRequest request = ctx.getRequest();
      if (CorsUtils.isCorsRequest(request)) {
        ServerHttpResponse response = ctx.getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
        headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
        headers.add("Access-Control-Max-Age", MAX_AGE);
        headers.add("Access-Control-Allow-Credentials", "true");
        if (request.getMethod() == HttpMethod.OPTIONS) {
          response.setStatusCode(HttpStatus.OK);
          return Mono.empty();
        }
      }
      return chain.filter(ctx);
    };
  }

  @SuppressWarnings("rawtypes")
  @Bean
  public RouterFunction routerFunction() {
    return RouterFunctions.route(RequestPredicates.GET("/swagger-resources")
            .and(RequestPredicates.accept(MediaType.ALL)), swaggerResourceHandler)
        .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
            .and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
        .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
            .and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);
  }
}
