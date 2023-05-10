package org.hobbit.user;

import org.hobbit.constant.AppConstant;
import org.hobbit.core.launch.HobbitApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

  public static void main(String[] args) {
    HobbitApplication.run(AppConstant.APPLICATION_USER, UserApplication.class, args);
  }

}
