package org.hobbit.auth.vo;

import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author lhy
 * @version 1.0.0 2023/5/10
 */
@ApiModel("刷新token")
@Data
public class RefreshTokenVO implements Serializable {

  @NotEmpty(message = "accessToken 不能为空")
  private String accessToken;

  @NotEmpty(message = "refreshToken 不能为空")
  private String refreshToken;
}
