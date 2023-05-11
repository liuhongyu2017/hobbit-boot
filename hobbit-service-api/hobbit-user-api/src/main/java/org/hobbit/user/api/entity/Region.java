package org.hobbit.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

/**
 * @author lhy
 * @version 1.0.0 2023/5/11
 */
@Data
@TableName("region")
@ApiModel(value = "Region", description = "行政区划")
public class Region implements Serializable {

  @NotEmpty(message = "区划编号不能为空")
  @TableId(value = "code", type = IdType.INPUT)
  @ApiModelProperty(value = "区划编号")
  private String code;

  @NotEmpty(message = "父区划编号不能为空")
  @ApiModelProperty(value = "父区划编号")
  private String parentCode;

  @ApiModelProperty(value = "祖区划编号")
  private String ancestors;

  @NotEmpty(message = "区划名称不能为空")
  @ApiModelProperty(value = "区划名称")
  private String name;

  @NotNull(message = "区划层级不能为空")
  @ApiModelProperty(value = "层级")
  private Integer regionLevel;

  @NotNull(message = "排序不能为空")
  @ApiModelProperty(value = "排序")
  private Integer sort;

  @ApiModelProperty(value = "备注")
  private String remark;
}
