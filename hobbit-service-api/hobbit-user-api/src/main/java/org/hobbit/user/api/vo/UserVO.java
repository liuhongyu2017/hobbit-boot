package org.hobbit.user.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hobbit.user.api.entity.User;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@ApiModel("UserVO")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends User {

  @ApiModelProperty("角色名")
  private String roleNames;

  @ApiModelProperty("岗位名")
  private String postNames;

  @ApiModelProperty("部门名")
  private String deptNames;
}
