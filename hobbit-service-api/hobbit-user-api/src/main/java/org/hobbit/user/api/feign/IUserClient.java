package org.hobbit.user.api.feign;

import org.hobbit.constant.AppConstant;
import org.hobbit.core.tool.api.R;
import org.hobbit.user.api.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@FeignClient(
    value = AppConstant.APPLICATION_USER,
    fallback = UserClientFallback.class
)
public interface IUserClient {

  String API_PREFIX = "/client";
  String GET_USER_BY_ACCOUNT = API_PREFIX + "/get/user/account";

  @GetMapping(GET_USER_BY_ACCOUNT)
  R<UserVO> getUserByAccount(@RequestParam("account") String account);
}
