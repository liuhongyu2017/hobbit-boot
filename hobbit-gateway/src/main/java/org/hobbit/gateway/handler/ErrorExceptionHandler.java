package org.hobbit.gateway.handler;

import java.util.Map;
import org.hobbit.gateway.provider.ResponseProvider;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
public class ErrorExceptionHandler extends DefaultErrorWebExceptionHandler {

  /**
   * Create a new {@code DefaultErrorWebExceptionHandler} instance.
   *
   * @param errorAttributes    the error attributes
   * @param resources          the resources configuration properties
   * @param errorProperties    the error configuration properties
   * @param applicationContext the current application context
   * @since 2.4.0
   */
  public ErrorExceptionHandler(
      ErrorAttributes errorAttributes,
      Resources resources,
      ErrorProperties errorProperties,
      ApplicationContext applicationContext) {
    super(errorAttributes, resources, errorProperties, applicationContext);
  }

  /**
   * 获取异常属性
   */
  @Override
  protected Map<String, Object> getErrorAttributes(ServerRequest request,
      ErrorAttributeOptions options) {
    int code = 500;
    Throwable error = super.getError(request);
    if (error instanceof NotFoundException) {
      code = 404;
    }
    if (error instanceof ResponseStatusException) {
      code = ((ResponseStatusException) error).getStatusCode().value();
    }
    return ResponseProvider.response(code, this.buildMessage(request, error));
  }

  /**
   * 指定响应处理方法为JSON处理的方法
   */
  @SuppressWarnings("NullableProblems")
  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
    return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
  }

  /**
   * 根据code获取对应的HttpStatus
   */
  @Override
  protected int getHttpStatus(Map<String, Object> errorAttributes) {
    return (int) errorAttributes.get("code");
  }

  /**
   * 构建异常信息
   */
  private String buildMessage(ServerRequest request, Throwable ex) {
    StringBuilder message = new StringBuilder("Failed to handle request [");
    message.append(request.method().name());
    message.append(" ");
    message.append(request.uri());
    message.append("]");
    if (ex != null) {
      message.append(": ");
      message.append(ex.getMessage());
    }
    return message.toString();
  }
}
