package com.wxxtest.rpc.registration.center.client.rpc.framework.bean;

import com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3.msg.RPCIdRequestTag;

import java.io.Serializable;

public class RegistryRequestInfo implements Serializable, RPCIdRequestTag {

  private long requestId;

  /**
   * 请求类型,4个字节
   */
  private int requestType;

  /**
   * 服务名称,64个字节
   */
  private String serviceName;

  /**
   * 地址，16个字节
   */
  private String address;

  /**
   * 端口，4个字节
   */
  private int port;

  public RegistryRequestInfo() {

  }

  public RegistryRequestInfo(int requestType, String serviceName, String address) {
    this.requestType = requestType;
    this.serviceName = serviceName;
    this.address = address;
  }

  public int getRequestType() {
    return requestType;
  }

  public void setRequestType(int requestType) {
    this.requestType = requestType;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  @Override
  public long getRequestId() {
    return requestId;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }

  @Override
  public String toString() {
    return "RegistryRequestInfo{" +
            "requestId=" + requestId +
            ", requestType=" + requestType +
            ", serviceName='" + serviceName + '\'' +
            ", address='" + address + '\'' +
            ", port=" + port +
            '}';
  }
}
