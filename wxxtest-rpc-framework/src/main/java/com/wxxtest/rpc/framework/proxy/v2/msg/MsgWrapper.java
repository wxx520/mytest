/*
 * Copyright (c) Jaguar Land Rover Ltd 2024. All rights reserved
 */

package com.wxxtest.rpc.framework.proxy.v2.msg;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class MsgWrapper {

  /**
   * a Future used to get the corresponding ack message
   */
  private CompletableFuture<Object> ackFuture;

  private Object[] params;

  private long requestId;

  private Class<?> returnType;

  private String serviceName;

  public CompletableFuture<Object> getAckFuture() {
    return ackFuture;
  }

  public void setAckFuture(CompletableFuture<Object> ackFuture) {
    this.ackFuture = ackFuture;
  }

  public Object[] getParams() {
    return params;
  }

  public void setParams(Object[] params) {
    this.params = params;
  }

  public long getRequestId() {
    return requestId;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }

  public Class<?> getReturnType() {
    return returnType;
  }

  public void setReturnType(Class<?> returnType) {
    this.returnType = returnType;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  @Override
  public String toString() {
    return "MsgWrapper{" +
            "ackFuture=" + ackFuture +
            ", params=" + Arrays.toString(params) +
            ", requestId=" + requestId +
            ", returnType=" + returnType +
            ", serviceName='" + serviceName + '\'' +
            '}';
  }
}
