package org.hobbit.auth.wrapper;

import org.hobbit.core.auth.HobbitUser;
import org.hobbit.user.api.vo.UserVO;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
public class HobbitUserWrapper {

  public static HobbitUser wrapper(UserVO userVO) {
    HobbitUser hobbitUser = new HobbitUser();

    hobbitUser.setUserId(userVO.getId());
    hobbitUser.setAccount(userVO.getAccount());
    hobbitUser.setRealName(userVO.getRealName());
    hobbitUser.setNickName(userVO.getNickName());
    hobbitUser.setDeptId(userVO.getDeptId());
    hobbitUser.setPostId(userVO.getPostId());
    hobbitUser.setRoleId(userVO.getRoleId());
    hobbitUser.setRoleName(userVO.getRoleNames());

    return hobbitUser;
  }
}
