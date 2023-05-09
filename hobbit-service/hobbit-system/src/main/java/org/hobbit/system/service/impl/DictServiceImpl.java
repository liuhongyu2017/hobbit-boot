package org.hobbit.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.hobbit.core.tool.node.ForestNodeMerger;
import org.hobbit.core.tool.node.TreeNode;
import org.hobbit.core.tool.utils.Func;
import org.hobbit.core.tool.utils.StringPool;
import org.hobbit.dict.api.entity.Dict;
import org.hobbit.dict.api.vo.DictVO;
import org.hobbit.system.mapper.DictMapper;
import org.hobbit.system.service.IDictService;
import org.springframework.stereotype.Service;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

  @Override
  public List<TreeNode> tree() {
    return ForestNodeMerger.merge(baseMapper.tree());
  }

  @Override
  public String getValue(String code, String dictKey) {
    return Func.toStr(baseMapper.getValue(code, dictKey), StringPool.EMPTY);
  }

  @Override
  public List<Dict> getList(String code) {
    return baseMapper.getList(code);
  }
}
