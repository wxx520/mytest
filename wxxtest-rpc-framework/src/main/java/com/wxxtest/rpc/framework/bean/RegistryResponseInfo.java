package com.wxxtest.rpc.framework.bean;

import com.wxxtest.rpc.framework.registry.v3.msg.RPCIdRequestTag;

import java.io.Serializable;
import java.util.Map;

public class RegistryResponseInfo implements Serializable, RPCIdRequestTag {

  private long requestId;

  private String serviceName;

  private Map<String, Integer> addresses;

  public RegistryResponseInfo() {
  }

  public RegistryResponseInfo(String serviceName, Map<String, Integer> addresses) {
    this.serviceName = serviceName;
    this.addresses = addresses;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public Map<String, Integer> getAddresses() {
    return addresses;
  }

  public void setAddresses(Map<String, Integer> addresses) {
    this.addresses = addresses;
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
    return "RegistryResponseInfo{" +
            "requestId=" + requestId +
            ", serviceName='" + serviceName + '\'' +
            ", addresses=" + addresses +
            '}';
  }
}
