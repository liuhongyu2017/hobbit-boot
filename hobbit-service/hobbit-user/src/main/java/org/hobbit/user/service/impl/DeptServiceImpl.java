package org.hobbit.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.hobbit.core.mybatis.service.impl.HobbitServiceImpl;
import org.hobbit.user.api.entity.Dept;
import org.hobbit.user.mapper.DeptMapper;
import org.hobbit.user.service.IDeptService;
import org.springframework.stereotype.Service;

/**
 * @author lhy
 * @version 1.0.0 2023/5/11
 */
@RequiredArgsConstructor
@Service
public class DeptServiceImpl extends HobbitServiceImpl<DeptMapper, Dept> implements IDeptService {

}
