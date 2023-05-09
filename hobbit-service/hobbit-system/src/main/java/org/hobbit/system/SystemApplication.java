package org.hobbit.system;

import org.hobbit.constant.AppConstant;
import org.hobbit.core.launch.HobbitApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
public class SystemApplication {

  public static void main(String[] args) {
    HobbitApplication.run(AppConstant.APPLICATION_SYSTEM_NAME, SystemApplication.class, args);
  }
}
