package org.hobbit.dict.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 字典枚举
 *
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
@Getter
@RequiredArgsConstructor
public enum DictEnum {

  /**
   * 性别
   */
  SEX("sex"),
  ;
  final String name;
}
