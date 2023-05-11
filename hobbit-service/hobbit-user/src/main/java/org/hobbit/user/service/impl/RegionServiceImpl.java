package org.hobbit.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.hobbit.core.log.exception.ServiceException;
import org.hobbit.core.tool.node.TreeNode;
import org.hobbit.core.tool.utils.Func;
import org.hobbit.core.tool.utils.StringPool;
import org.hobbit.user.api.entity.Region;
import org.hobbit.user.api.vo.RegionVO;
import org.hobbit.user.mapper.RegionMapper;
import org.hobbit.user.service.IRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lhy
 * @version 1.0.0 2023/5/11
 */
@RequiredArgsConstructor
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean submit(Region region) {
    Long count = baseMapper.selectCount(
        Wrappers.<Region>lambdaQuery().eq(Region::getCode, region.getCode()));
    if (Objects.equals(region.getCode(), region.getParentCode())) {
      throw new ServiceException("不能是自己的下级");
    }
    if (count > 0) {
      return this.updateById(region);
    }
    Region parent = getById(region.getParentCode());
    if (Func.isNotEmpty(parent) || Func.isNotEmpty(parent.getCode())) {
      String ancestors = parent.getAncestors() + StringPool.COMMA + parent.getCode();
      region.setAncestors(ancestors);
    }
    return save(region);
  }

  @Override
  public List<TreeNode> tree() {
    return baseMapper.tree();
  }

  @Override
  public RegionVO detail(String code) {
    return baseMapper.detail(code);
  }
}
