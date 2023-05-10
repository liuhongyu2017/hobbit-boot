package org.hobbit.auth;

import org.hobbit.constant.AppConstant;
import org.hobbit.core.launch.HobbitApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
public class AuthApplication {

  public static void main(String[] args) {
    HobbitApplication.run(AppConstant.APPLICATION_AUTH, AuthApplication.class, args);
  }

}
