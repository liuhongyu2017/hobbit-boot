package org.hobbit.gateway.props;

import lombok.Data;
import org.hobbit.core.launch.constant.AppConstant;

/**
 * Swagger聚合文档属性
 *
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Data
public class RouteResource {

  /**
   * 文档名
   */
  private String name;

  /**
   * 文档所在服务地址
   */
  private String location;

  /**
   * 文档版本
   */
  private String version = AppConstant.APPLICATION_VERSION;
}
