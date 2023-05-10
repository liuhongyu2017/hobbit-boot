package org.hobbit.user.api.feign;

import lombok.extern.slf4j.Slf4j;
import org.hobbit.core.tool.api.R;
import org.hobbit.user.api.vo.UserVO;
import org.springframework.stereotype.Component;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@Slf4j
@Component
public class UserClientFallback implements IUserClient {

  @Override
  public R<UserVO> getUserByAccount(String account) {
    return R.fail("调用失败");
  }
}
