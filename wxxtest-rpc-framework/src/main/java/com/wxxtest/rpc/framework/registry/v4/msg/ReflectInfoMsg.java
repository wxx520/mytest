package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v4.msg;

import java.lang.reflect.Method;

public class ReflectInfoMsg {

  public ReflectInfoMsg(Method method, Object methodInstance) {
    this.method = method;
    this.methodInstance = methodInstance;
  }

  private Method method;

  private Object methodInstance;

  public Method getMethod() {
    return method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public Object getMethodInstance() {
    return methodInstance;
  }

  public void setMethodInstance(Object methodInstance) {
    this.methodInstance = methodInstance;
  }

  @Override
  public String toString() {
    return "ReflectMsg{" +
            ", method=" + method +
            ", methodInstance=" + methodInstance +
            '}';
  }

}
