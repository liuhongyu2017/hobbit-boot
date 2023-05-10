package org.hobbit.gateway.props;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 权限过滤
 *
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Data
@RefreshScope
@ConfigurationProperties("hobbit.secure")
public class AuthProperties {

  /**
   * 放行API集合
   */
  private final List<String> skipUrl = new ArrayList<>();
}
