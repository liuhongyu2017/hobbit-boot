package org.hobbit.gateway.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Slf4j
@Component
@AllArgsConstructor
public class SwaggerResourceHandler implements HandlerFunction<ServerResponse> {

  private final SwaggerResourcesProvider swaggerResources;

  /**
   * Handle the given request.
   *
   * @param request the request to handler
   * @return the response
   */
  @SuppressWarnings("NullableProblems")
  @Override
  public Mono<ServerResponse> handle(ServerRequest request) {
    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(swaggerResources.get()));
  }

}
