package org.hobbit.dict.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author lhy
 * @version 1.0.0 2023/5/9
 */
@Data
@TableName("dict")
@ApiModel(value = "Dict对象", description = "Dict对象")
public class Dict implements Serializable {

  @JsonSerialize(using = ToStringSerializer.class)
  @ApiModelProperty("主键")
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  @JsonSerialize(using = ToStringSerializer.class)
  @ApiModelProperty("父主键")
  private Long parentId;

  @ApiModelProperty("字典编码")
  private String code;

  @ApiModelProperty("字典值")
  private String dictKey;

  @ApiModelProperty("字典内容")
  private String dictValue;

  @ApiModelProperty("排序")
  private Integer sort;

  @ApiModelProperty("备注")
  private String remark;

  @ApiModelProperty("是否封存")
  private Integer sealed;

  @TableLogic
  @ApiModelProperty("是否已删除")
  private Integer deleted;
}
