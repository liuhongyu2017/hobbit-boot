package org.hobbit.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.hobbit.core.tool.node.TreeNode;
import org.hobbit.dict.api.entity.Dict;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
public interface IDictService extends IService<Dict> {

  /**
   * 树形结构
   */
  List<TreeNode> tree();

  /**
   * 获取对应 value
   */
  String getValue(String code, String dictKey);

  /**
   * 获取 list
   */
  List<Dict> getList(String code);
}
