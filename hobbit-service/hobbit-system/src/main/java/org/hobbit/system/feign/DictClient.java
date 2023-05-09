package org.hobbit.system.feign;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hobbit.core.tool.api.R;
import org.hobbit.dict.api.entity.Dict;
import org.hobbit.dict.api.feign.IDictClient;
import org.hobbit.system.service.IDictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
@RequiredArgsConstructor
@ApiIgnore
@RestController
public class DictClient implements IDictClient {

  private final IDictService dictService;

  @GetMapping(GET_BY_ID)
  @Override
  public R<Dict> getById(Long id) {
    return R.data(dictService.getById(id));
  }

  @GetMapping(GET_VALUE)
  @Override
  public R<String> getValue(String code, String dictKey) {
    return R.data(dictService.getValue(code, dictKey));
  }

  @GetMapping(GET_LIST)
  @Override
  public R<List<Dict>> getList(String code) {
    return R.data(dictService.getList(code));
  }
}
