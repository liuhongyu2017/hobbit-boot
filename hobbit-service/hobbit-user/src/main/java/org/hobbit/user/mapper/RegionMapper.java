package org.hobbit.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.hobbit.core.tool.node.TreeNode;
import org.hobbit.user.api.entity.Region;
import org.hobbit.user.api.vo.RegionVO;

/**
 * @author lhy
 * @version 1.0.0 2023/5/11
 */
public interface RegionMapper extends BaseMapper<Region> {

  /**
   * 树
   */
  List<TreeNode> tree();

  /**
   * 详情
   */
  RegionVO detail(String code);
}
