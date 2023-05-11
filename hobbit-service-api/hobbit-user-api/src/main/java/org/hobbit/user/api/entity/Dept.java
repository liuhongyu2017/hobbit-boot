package org.hobbit.user.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hobbit.core.mybatis.base.BaseEntity;
import org.hobbit.core.tool.support.Kv;

/**
 * @author lhy
 * @version 1.0.0 2023/5/11
 */
@TableName(autoResultMap = true)
@ApiModel(value = "Dept", description = "部门")
@EqualsAndHashCode(callSuper = true)
@Data
public class Dept extends BaseEntity {

  @JsonSerialize(using = ToStringSerializer.class)
  @ApiModelProperty("父id")
  private Long parentId;

  @JsonSerialize(using = ToStringSerializer.class)
  @ApiModelProperty("行政区划编码")
  private Long regionCode;

  @ApiModelProperty("部门名")
  private String deptName;

  @ApiModelProperty("部门全名")
  private String fullName;

  @ApiModelProperty("祖级机构主键")
  private String ancestors;

  @ApiModelProperty("部门类型")
  private String deptCategory;

  @ApiModelProperty("排序")
  private Integer sort;

  @ApiModelProperty("备注")
  private String remark;

  @TableField(typeHandler = JacksonTypeHandler.class)
  @ApiModelProperty("扩展信息")
  private Kv detail;
}
