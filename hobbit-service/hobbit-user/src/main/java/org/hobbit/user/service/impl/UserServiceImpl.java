package org.hobbit.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.hobbit.core.mybatis.service.impl.HobbitServiceImpl;
import org.hobbit.user.api.entity.User;
import org.hobbit.user.mapper.UserMapper;
import org.hobbit.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author lhy
 * @version 1.0.0 2023/5/11
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends HobbitServiceImpl<UserMapper, User> implements IUserService {

}
