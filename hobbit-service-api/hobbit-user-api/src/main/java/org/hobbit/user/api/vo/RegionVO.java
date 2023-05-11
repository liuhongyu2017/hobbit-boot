package org.hobbit.user.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hobbit.core.tool.node.INode;
import org.hobbit.core.tool.utils.Func;
import org.hobbit.user.api.entity.Region;

/**
 * @author lhy
 * @version 1.0.0 2023/5/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "RegionVO", description = "行政区划表")
public class RegionVO extends Region implements INode<RegionVO> {

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long parentId;

  private String parentName;

  @JsonInclude(Include.NON_EMPTY)
  private Boolean hasChildren;

  @JsonInclude(Include.NON_EMPTY)
  private List<RegionVO> children;

  @Override
  public Long getId() {
    return Func.toLong(this.getCode());
  }

  @Override
  public Long getParentId() {
    return Func.toLong(this.getParentCode());
  }

  @Override
  public List<RegionVO> getChildren() {
    if (this.children == null) {
      this.children = new ArrayList<>();
    }
    return this.children;
  }
}
