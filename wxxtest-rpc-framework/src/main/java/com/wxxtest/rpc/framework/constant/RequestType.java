package com.wxxtest.rpc.registration.center.client.rpc.framework.constant;

public enum RequestType {

  REGISTRY_SERVICE(1, "服务注册"),

  REGISTRY_FIND(2, "服务查询");

  private int code;
  private String msg;

  private RequestType(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static RequestType getType(int code) {
    if (code == 1) {
      return REGISTRY_SERVICE;
    } else if (code == 2) {
      return REGISTRY_FIND;
    } else {
      return null;
    }
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
