package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.msg;

public class ResponseWrapper<T> {
  private long requestId;

  private String methodName;

  private T request;

  public ResponseWrapper(long requestId, T request, String methodName) {
    this.requestId = requestId;
    this.request = request;
    this.methodName = methodName;
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

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  @Override
  public String toString() {
    return "ResponseWrapper{" +
            "requestId=" + requestId +
            ", methodName='" + methodName + '\'' +
            ", request=" + request +
            '}';
  }
}
