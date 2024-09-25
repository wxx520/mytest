package com.wxxtest.rpc.framework.registry.v5;

import com.wxxtest.rpc.framework.registry.v4.msg.ReflectInfoMsg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegistryServiceManager {

  private final Map<String, ReflectInfoMsg> realServiceMap;

  public RegistryServiceManager() {
    realServiceMap = new HashMap<>();
  }

  public synchronized <T> void registryService(Class<T> serviceClazz, T instance) {
    String className = serviceClazz.getName();
    for (Method method : serviceClazz.getMethods()) {
      realServiceMap.put(concatFullServiceName(className, method.getName()), new ReflectInfoMsg(method, instance));
    }
  }

  private String concatFullServiceName(String className, String methodName) {
    return className + "." + methodName;
  }

  public synchronized Class<?> removeService(Class<?> serviceClazz) {
    String className = serviceClazz.getName();
    ReflectInfoMsg serviceInfo = realServiceMap.remove(className);
    if (serviceInfo == null) {
      return null;
    }
    for (Method method : serviceClazz.getMethods()) {
      realServiceMap.remove(concatFullServiceName(className, method.getName()));
    }
    return serviceInfo.getMethodInstance().getClass();
  }

  /**
   * 根据服务名称找到具体的服务
   * 执行后返回执行的结果
   */
  public synchronized Object invokeRealService(String serviceName, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    ReflectInfoMsg serviceInfo = realServiceMap.get(serviceName);
    if (serviceInfo == null) {
      throw new NoSuchMethodException();
    }
    //根据找到的真实的服务执行，并返回结果
    Method realMethod = serviceInfo.getMethod();
    Object instance = serviceInfo.getMethodInstance();
    return realMethod.invoke(instance, args);
  }

  public synchronized Method getMethod(String serviceName) {
    return realServiceMap.get(serviceName).getMethod();
  }
}
