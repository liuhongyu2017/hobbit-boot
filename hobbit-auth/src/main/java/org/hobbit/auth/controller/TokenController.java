package org.hobbit.auth.controller;

import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import java.time.Duration;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hobbit.auth.utils.TokenUtil;
import org.hobbit.auth.vo.LoginVO;
import org.hobbit.auth.vo.RefreshTokenVO;
import org.hobbit.auth.wrapper.HobbitUserWrapper;
import org.hobbit.constant.CacheNameConstant;
import org.hobbit.core.auth.HobbitUser;
import org.hobbit.core.auth.exception.SecureException;
import org.hobbit.core.redis.cache.HobbitRedis;
import org.hobbit.core.tool.api.R;
import org.hobbit.core.tool.constant.TokenConstant;
import org.hobbit.core.tool.support.Kv;
import org.hobbit.core.tool.utils.DigestUtil;
import org.hobbit.core.tool.utils.StringUtil;
import org.hobbit.user.api.vo.UserVO;
import org.hobbit.user.api.feign.IUserClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Api(value = "鉴权")
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class TokenController {

  private final HobbitRedis hobbitRedis;
  private final TokenUtil tokenUtil;
  private final IUserClient userClient;

  @ApiOperation("获取验证码")
  @GetMapping("/captcha")
  public Kv captcha() {
    SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
    String verCode = specCaptcha.text().toLowerCase();
    String key = StringUtil.randomUUID();
    // 验证码过期时间，分钟
    hobbitRedis.set(CacheNameConstant.CAPTCHA_KEY + key, verCode, Duration.ofMinutes(30));
    return Kv.create().set("key", key).set("image", specCaptcha.toBase64());
  }

  /**
   * 获取 token
   */
  @PostMapping("/token")
  public R<Kv> token(@Valid @RequestBody LoginVO loginVO) {
    R<UserVO> userVOR = userClient.getUserByAccount(loginVO.getAccount());
    if (!userVOR.isSuccess()) {
      throw new SecureException("获取用户信息错误：" + userVOR.getMsg());
    }
    UserVO userVO = userVOR.getData();
    // 验证码
    String captcha = hobbitRedis.get(CacheNameConstant.CAPTCHA_KEY + loginVO.getKey(),
        String.class);
    if (!Objects.equals(captcha, loginVO.getCaptcha())) {
      throw new SecureException("验证码错误");
    }
    // 密码
    String decrypt = DigestUtil.encrypt(loginVO.getPassword());
//    if (!Objects.equals(decrypt, userVO.getPassword())) {
//      throw new SecureException("密码错误");
//    }
    String accessToken = tokenUtil.getToken();
    String refreshToken = tokenUtil.getToken();

    HobbitUser hobbitUser = HobbitUserWrapper.wrapper(userVO);
    hobbitUser.setAccessToken(accessToken);
    hobbitUser.setRefreshToken(refreshToken);

    hobbitRedis.set(tokenUtil.getAccessTokenKey(accessToken), hobbitUser,
        Duration.ofMinutes(TokenUtil.expires));
    hobbitRedis.set(tokenUtil.getAccessTokenKey(refreshToken), hobbitUser,
        Duration.ofDays(TokenUtil.refreshExpires));
    return R.data(Kv.create().set(TokenConstant.ACCESS_TOKEN, accessToken)
        .set(TokenConstant.REFRESH_TOKEN, refreshToken));
  }


  @ApiOperation("刷新token")
  @PostMapping("/refreshToken")
  public R<Kv> refreshToken(@Valid @RequestBody RefreshTokenVO refreshTokenVO) {
    HobbitUser hobbitUser = hobbitRedis.get(
        tokenUtil.getAccessTokenKey(refreshTokenVO.getRefreshToken()), HobbitUser.class);
    String accessToken = hobbitUser.getAccessToken();
    String refreshToken = hobbitUser.getRefreshToken();
    if (!Objects.equals(accessToken, refreshTokenVO.getAccessToken())) {
      throw new SecureException("token 无效");
    }
    accessToken = tokenUtil.getToken();
    hobbitUser.setAccessToken(accessToken);
    RedisTemplate<String, Object> redisTemplate = hobbitRedis.getRedisTemplate();
    redisTemplate.multi();
    redisTemplate.delete(tokenUtil.getAccessTokenKey(accessToken));
    redisTemplate.opsForValue().set(tokenUtil.getAccessTokenKey(accessToken), hobbitUser,
        Duration.ofMinutes(TokenUtil.expires));
    redisTemplate.opsForValue().set(tokenUtil.getAccessTokenKey(refreshToken), hobbitUser,
        Duration.ofDays(TokenUtil.refreshExpires));
    redisTemplate.exec();
    return R.data(Kv.create().set(TokenConstant.ACCESS_TOKEN, accessToken)
        .set(TokenConstant.REFRESH_TOKEN, refreshToken));
  }
}
