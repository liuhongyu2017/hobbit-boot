package org.hobbit.gateway.props;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 路由配置类
 *
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Data
@RefreshScope
@ConfigurationProperties("hobbit.document")
public class RouteProperties {

  private final List<RouteResource> resources = new ArrayList<>();
}
