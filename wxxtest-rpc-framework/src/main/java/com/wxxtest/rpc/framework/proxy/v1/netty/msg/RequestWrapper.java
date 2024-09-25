package com.wxxtest.rpc.registration.center.client.rpc.framework.proxy.v1.netty.msg;

public class RequestWrapper<T> {
  private long requestId;

  private T request;

  public RequestWrapper(long requestId, T request) {
    this.requestId = requestId;
    this.request = request;
  }

  public long getRequestId() {
    return requestId;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }

  public T getRequest() {
    return request;
  }

  public void setRequest(T request) {
    this.request = request;
  }
}
