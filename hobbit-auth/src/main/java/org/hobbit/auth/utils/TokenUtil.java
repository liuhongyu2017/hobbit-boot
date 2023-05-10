package org.hobbit.auth.utils;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.hobbit.constant.CacheNameConstant;
import org.hobbit.core.auth.props.TokenProperties;
import org.hobbit.core.tool.constant.TokenConstant;
import org.hobbit.core.tool.utils.WebUtil;
import org.springframework.stereotype.Component;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@RequiredArgsConstructor
@Component
public class TokenUtil {

  private final TokenProperties tokenProperties;

  public final static String REGION_CODE = "region_code";
  // 分钟
  public final static int expires = 120;
  // 天
  public final static int refreshExpires = 30;
  /**
   * 授权码默认16位密钥
   */
  public final static String DES_KEY = "0000000000000000";

  /**
   * 获取请求头中的客户端id
   */
  public String getClientIdFromHeader() {
    return WebUtil.getRequest().getHeader(TokenConstant.CLIENT_ID);
  }

  /**
   * 获取token索引
   */
  public String getAccessTokenKey(String token) {
    return CacheNameConstant.TOKEN_CACHE.concat("::").concat(token);
  }

  /**
   * 生成 token
   */
  public String getToken() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
