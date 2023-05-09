package org.hobbit.dict.api.feign;

import java.util.List;
import org.hobbit.core.tool.api.R;
import org.hobbit.dict.api.entity.Dict;
import org.springframework.stereotype.Component;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
@Component
public class DictClientFallback implements IDictClient {

  @Override
  public R<Dict> getById(Long id) {
    return R.fail("获取数据失败");
  }

  @Override
  public R<String> getValue(String code, String dictKey) {
    return R.fail("获取数据失败");
  }

  @Override
  public R<List<Dict>> getList(String code) {
    return R.fail("获取数据失败");
  }
}
