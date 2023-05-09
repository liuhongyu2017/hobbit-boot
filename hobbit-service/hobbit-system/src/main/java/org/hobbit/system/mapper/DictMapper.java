package org.hobbit.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.hobbit.core.tool.node.TreeNode;
import org.hobbit.dict.api.entity.Dict;
import org.hobbit.dict.api.vo.DictVO;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
public interface DictMapper extends BaseMapper<Dict> {

  /**
   * 数形结构
   */
  List<TreeNode> tree();

  /**
   * 获取 value
   */
  String getValue(String code, String dictKey);

  /**
   * 根据 code 获取 list
   */
  List<Dict> getList(String code);
}
