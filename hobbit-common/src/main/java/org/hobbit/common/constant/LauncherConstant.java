package org.hobbit.common.constant;

import org.hobbit.core.launch.constant.AppConstant;

/**
 * 启动常量
 */
public interface LauncherConstant {

  /**
   * nacos DEV 命名空间
   */
  String NACOS_DEV_NAMESPACE = "";
  /**
   * nacos PROP 命名空间
   */
  String NACOS_PROP_NAMESPACE = "";
  /**
   * nacos TEST 命名空间
   */
  String NACOS_TEST_NAMESPACE = "";

  /**
   * nacos dev 地址
   */
  String NACOS_DEV_ADDR = "10.1.199.31:8848";
  /**
   * nacos prod 地址
   */
  String NACOS_PROD_ADDR = "10.1.199.31:8848";
  /**
   * nacos test 地址
   */
  String NACOS_TEST_ADDR = "10.1.199.31:8848";

  /**
   * sentinel dev 地址
   */
  String SENTINEL_DEV_ADDR = "127.0.0.1:8858";
  /**
   * sentinel prod 地址
   */
  String SENTINEL_PROD_ADDR = "172.20.81.227:8858";
  /**
   * sentinel test 地址
   */
  String SENTINEL_TEST_ADDR = "172.30.0.58:8858";

  /**
   * seata dev 地址
   */
  String SEATA_DEV_ADDR = "127.0.0.1:8091";
  /**
   * seata prod 地址
   */
  String SEATA_PROD_ADDR = "172.30.0.68:8091";
  /**
   * seata test 地址
   */
  String SEATA_TEST_ADDR = "172.30.0.68:8091";

  /**
   * zipkin dev 地址
   */
  String ZIPKIN_DEV_ADDR = "http://127.0.0.1:9411";
  /**
   * zipkin prod 地址
   */
  String ZIPKIN_PROD_ADDR = "http://127.0.0.1:9411";
  /**
   * zipkin test 地址
   */
  String ZIPKIN_TEST_ADDR = "http://172.30.0.71:9411";

  /**
   * elk dev 地址
   */
  String ELK_DEV_ADDR = "127.0.0.1:9000";
  /**
   * elk prod 地址
   */
  String ELK_PROD_ADDR = "172.30.0.72:9000";
  /**
   * elk test 地址
   */
  String ELK_TEST_ADDR = "39.108.214.103:9000";

  /**
   * seata file模式
   */
  String FILE_MODE = "file";
  /**
   * seata nacos模式
   */
  String NACOS_MODE = "nacos";
  /**
   * seata default模式
   */
  String DEFAULT_MODE = "default";

  /**
   * seata group后缀
   */
  String GROUP_NAME = "-group";

  /**
   * seata 服务组格式
   *
   * @param appName 服务名
   * @return group
   */
  static String seataServiceGroup(String appName) {
    return appName.concat(GROUP_NAME);
  }

  static String nacosNamespace(String profile) {
    return switch (profile) {
      case (AppConstant.PROD_CODE) -> NACOS_PROP_NAMESPACE;
      case (AppConstant.TEST_CODE) -> NACOS_TEST_NAMESPACE;
      default -> NACOS_DEV_NAMESPACE;
    };
  }

  /**
   * 动态获取nacos地址
   *
   * @param profile 环境变量
   * @return addr
   */
  static String nacosAddr(String profile) {
    return switch (profile) {
      case (AppConstant.PROD_CODE) -> NACOS_PROD_ADDR;
      case (AppConstant.TEST_CODE) -> NACOS_TEST_ADDR;
      default -> NACOS_DEV_ADDR;
    };
  }

  /**
   * 动态获取sentinel地址
   *
   * @param profile 环境变量
   * @return addr
   */
  static String sentinelAddr(String profile) {
    return switch (profile) {
      case (AppConstant.PROD_CODE) -> SENTINEL_PROD_ADDR;
      case (AppConstant.TEST_CODE) -> SENTINEL_TEST_ADDR;
      default -> SENTINEL_DEV_ADDR;
    };
  }

  /**
   * 动态获取seata地址
   *
   * @param profile 环境变量
   * @return addr
   */
  static String seataAddr(String profile) {
    return switch (profile) {
      case (AppConstant.PROD_CODE) -> SEATA_PROD_ADDR;
      case (AppConstant.TEST_CODE) -> SEATA_TEST_ADDR;
      default -> SEATA_DEV_ADDR;
    };
  }

  /**
   * 动态获取zipkin地址
   *
   * @param profile 环境变量
   * @return addr
   */
  static String zipkinAddr(String profile) {
    return switch (profile) {
      case (AppConstant.PROD_CODE) -> ZIPKIN_PROD_ADDR;
      case (AppConstant.TEST_CODE) -> ZIPKIN_TEST_ADDR;
      default -> ZIPKIN_DEV_ADDR;
    };
  }

  /**
   * 动态获取elk地址
   *
   * @param profile 环境变量
   * @return addr
   */
  static String elkAddr(String profile) {
    return switch (profile) {
      case (AppConstant.PROD_CODE) -> ELK_PROD_ADDR;
      case (AppConstant.TEST_CODE) -> ELK_TEST_ADDR;
      default -> ELK_DEV_ADDR;
    };
  }

}
