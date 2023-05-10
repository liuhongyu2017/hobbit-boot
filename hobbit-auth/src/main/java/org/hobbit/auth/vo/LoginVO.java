package org.hobbit.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@ApiModel("登入")
@Data
public class LoginVO implements Serializable {

  @NotEmpty(message = "登入名不能为空")
  @ApiModelProperty("账户")
  private String account;

  @NotEmpty(message = "密码不能为空")
  @ApiModelProperty("密码")
  private String password;

  @NotEmpty(message = "key 不能为空")
  @ApiModelProperty("验证码 key")
  private String key;

  @NotEmpty(message = "验证码不能为空")
  @ApiModelProperty("验证码")
  private String captcha;
}
