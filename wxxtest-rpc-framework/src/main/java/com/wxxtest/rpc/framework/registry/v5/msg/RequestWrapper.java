package com.wxxtest.rpc.framework.registry.v5.msg;

public class RequestWrapper {

  private long requestId;

  private String methodName;

  private Object request;

  public RequestWrapper(long requestId, Object request, String methodName) {
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

  public Object getRequest() {
    return request;
  }

  public void setRequest(Object request) {
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
