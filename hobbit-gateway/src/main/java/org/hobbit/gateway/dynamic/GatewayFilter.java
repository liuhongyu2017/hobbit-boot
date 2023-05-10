package org.hobbit.gateway.dynamic;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * 过滤器定义模型
 *
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Data
public class GatewayFilter {

  /**
   * 过滤器对应的Name
   */
  private String name;

  /**
   * 对应的路由规则
   */
  private Map<String, String> args = new LinkedHashMap<>();
}
