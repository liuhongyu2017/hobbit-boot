package org.hobbit.dict.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hobbit.core.tool.node.INode;
import org.hobbit.dict.api.entity.Dict;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "DictVO对象", description = "DictVO对象")
public class DictVO extends Dict implements INode<DictVO> {

  /**
   * 主键ID
   */
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  /**
   * 父节点ID
   */
  @JsonSerialize(using = ToStringSerializer.class)
  private Long parentId;

  /**
   * 子孙节点
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<DictVO> children;
  /**
   * 上级字典
   */
  private String parentName;

  @Override
  public List<DictVO> getChildren() {
    if (this.children == null) {
      this.children = new ArrayList<>();
    }
    return this.children;
  }
}
