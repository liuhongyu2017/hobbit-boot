package org.hobbit.constant;

/**
 * 缓存名常量
 *
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
public interface CacheNameConstant {

  String CAPTCHA_KEY = "hobbit:auth::rapid:captcha:";
  /**
   * token保存至redis的key
   */
  String TOKEN_CACHE = "hobbit:token";
}
