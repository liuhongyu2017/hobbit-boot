package org.hobbit.user.feigin;

import org.hobbit.core.tool.api.R;
import org.hobbit.user.api.vo.UserVO;
import org.hobbit.user.api.feign.IUserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@RestController
public class UserClient implements IUserClient {

  @GetMapping(GET_USER_BY_ACCOUNT)
  @Override
  public R<UserVO> getUserByAccount(String account) {
    UserVO userVO = new UserVO();
    userVO.setAccount("张三");
    userVO.setDeptNames("部门");
    userVO.setPostNames("岗位");
    userVO.setRoleNames("角色");
    return R.data(userVO);
  }
}
