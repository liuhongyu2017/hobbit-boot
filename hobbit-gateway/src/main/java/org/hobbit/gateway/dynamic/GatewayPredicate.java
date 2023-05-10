package org.hobbit.gateway.dynamic;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * 路由断言定义模型
 *
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Data
public class GatewayPredicate {

  /**
   * 断言对应的Name
   */
  private String name;

  /**
   * 配置的断言规则
   */
  private Map<String, String> args = new LinkedHashMap<>();
}
