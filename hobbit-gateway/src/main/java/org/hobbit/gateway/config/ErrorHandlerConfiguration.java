package org.hobbit.gateway.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hobbit.gateway.handler.ErrorExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

/**
 * 异常处理
 *
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@RequiredArgsConstructor
@Configuration
@AutoConfigureBefore(ErrorWebFluxAutoConfiguration.class)
@EnableConfigurationProperties({ServerProperties.class})
public class ErrorHandlerConfiguration {

  private final ServerProperties serverProperties;
  private final ApplicationContext applicationContext;
  private final WebProperties webProperties;
  private final List<ViewResolver> viewResolvers;
  private final ServerCodecConfigurer serverCodecConfigurer;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes) {
    ErrorExceptionHandler exceptionHandler = new ErrorExceptionHandler(
        errorAttributes,
        this.webProperties.getResources(),
        this.serverProperties.getError(),
        this.applicationContext);
    exceptionHandler.setViewResolvers(this.viewResolvers);
    exceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
    exceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
    return exceptionHandler;
  }
}
