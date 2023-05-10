package org.hobbit.gateway;

import org.hobbit.constant.AppConstant;
import org.hobbit.core.launch.HobbitApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    HobbitApplication.run(AppConstant.APPLICATION_GATEWAY, GatewayApplication.class, args);
  }

}
