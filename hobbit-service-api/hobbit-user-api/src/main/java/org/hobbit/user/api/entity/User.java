package org.hobbit.user.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hobbit.core.mybatis.base.BaseEntity;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@ApiModel(value = "User", description = "用户表")
@TableName("user")
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {

  @ApiModelProperty("用户编号")
  private String code;

  @ApiModelProperty("账号")
  private String account;

  @ApiModelProperty("密码")
  private String password;

  @ApiModelProperty("昵称")
  private String nickName;

  @ApiModelProperty("真名")
  private String realName;

  @ApiModelProperty("头像")
  private String avatar;

  @ApiModelProperty("角色")
  private String roleId;

  @ApiModelProperty("部门")
  private String deptId;

  @ApiModelProperty("岗位")
  private String postId;
}
