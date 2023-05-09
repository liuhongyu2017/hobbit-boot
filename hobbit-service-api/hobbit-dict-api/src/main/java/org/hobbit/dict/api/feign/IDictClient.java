package org.hobbit.dict.api.feign;

import java.util.List;
import org.hobbit.constant.AppConstant;
import org.hobbit.core.tool.api.R;
import org.hobbit.dict.api.entity.Dict;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
@FeignClient(
    value = AppConstant.APPLICATION_SYSTEM_NAME,
    fallback = DictClientFallback.class
)
public interface IDictClient {

  String API_PREFIX = "/client";
  String GET_BY_ID = API_PREFIX + "/dict/get-by-id";
  String GET_VALUE = API_PREFIX + "/dict/get-value";
  String GET_LIST = API_PREFIX + "/dict/get-list";

  /**
   * 获取字典实体
   *
   * @param id 主键
   */
  @GetMapping(GET_BY_ID)
  R<Dict> getById(@RequestParam("id") Long id);

  /**
   * 获取字典表对应值
   *
   * @param code    字典编号
   * @param dictKey 字典序号
   */
  @GetMapping(GET_VALUE)
  R<String> getValue(@RequestParam("code") String code, @RequestParam("dictKey") String dictKey);

  /**
   * 获取字典表
   *
   * @param code 字典编号
   */
  @GetMapping(GET_LIST)
  R<List<Dict>> getList(@RequestParam("code") String code);
}
