package org.hobbit.dict.api.cache;

import java.util.List;
import java.util.Optional;
import org.hobbit.core.redis.cache.CacheUtil;
import org.hobbit.core.redis.constant.CacheConstant;
import org.hobbit.core.tool.api.R;
import org.hobbit.core.tool.utils.SpringUtil;
import org.hobbit.core.tool.utils.StringPool;
import org.hobbit.dict.api.entity.Dict;
import org.hobbit.dict.api.enums.DictEnum;
import org.hobbit.dict.api.feign.IDictClient;

/**
 * 字段缓存
 *
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
public class DictCache {

  private static final String DICT_ID = "dict:id:";
  private static final String DICT_KEY = "dict:key:";
  private static final String DICT_VALUE = "dict:value:";
  private static final String DICT_LIST = "dict:list:";

  private static IDictClient dictClient;
  private static CacheUtil cacheUtil;

  private static IDictClient getDictClient() {
    if (dictClient == null) {
      dictClient = SpringUtil.getBean(IDictClient.class);
    }
    return dictClient;
  }

  private static CacheUtil getCacheUtil() {
    if (cacheUtil == null) {
      cacheUtil = SpringUtil.getBean(CacheUtil.class);
    }
    return cacheUtil;
  }

  /**
   * 获取字典实体
   *
   * @param id 主键
   */
  public static Dict getById(Long id) {
    return getCacheUtil().get(CacheConstant.DICT_CACHE, DICT_ID, id, () -> {
      R<Dict> result = getDictClient().getById(id);
      return result.getData();
    });
  }

  /**
   * 获取字典键
   *
   * @param code      字典编号
   * @param dictValue 字典值
   */
  public static String getKey(String code, String dictValue) {
    return getCacheUtil().get(CacheConstant.DICT_CACHE, DICT_KEY + code + StringPool.COLON,
        dictValue, () -> {
          List<Dict> list = getList(code);
          Optional<String> key = list.stream().filter(
              dict -> dict.getDictValue().equalsIgnoreCase(dictValue)
          ).map(Dict::getDictKey).findFirst();
          return key.orElse(StringPool.EMPTY);
        });
  }

  /**
   * 获取字典值
   *
   * @param code    字典编号枚举
   * @param dictKey Integer型字典键
   */
  public static String getValue(DictEnum code, Integer dictKey) {
    return getValue(code.getName(), dictKey);
  }


  /**
   * 获取字典值
   *
   * @param code    字典编号
   * @param dictKey Integer型字典键
   */
  public static String getValue(String code, Integer dictKey) {
    return getCacheUtil().get(CacheConstant.DICT_CACHE, DICT_VALUE + code + StringPool.COLON,
        String.valueOf(dictKey),
        () -> {
          R<String> result = getDictClient().getValue(code, String.valueOf(dictKey));
          return result.getData();
        });
  }

  /**
   * 获取字典值
   *
   * @param code    字典编号枚举
   * @param dictKey String型字典键
   */
  public static String getValue(DictEnum code, String dictKey) {
    return getValue(code.getName(), dictKey);
  }

  /**
   * 获取字典值
   *
   * @param code    字典编号
   * @param dictKey String型字典键
   */
  public static String getValue(String code, String dictKey) {
    return getCacheUtil().get(CacheConstant.DICT_CACHE, DICT_VALUE + code + StringPool.COLON,
        dictKey, () -> {
          R<String> result = getDictClient().getValue(code, dictKey);
          return result.getData();
        });
  }

  /**
   * 获取字典集合
   *
   * @param code 字典编号
   */
  public static List<Dict> getList(String code) {
    return getCacheUtil().get(CacheConstant.DICT_CACHE, DICT_LIST, code, () -> {
      R<List<Dict>> result = getDictClient().getList(code);
      return result.getData();
    });
  }
}
