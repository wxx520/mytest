package com.wxxtest.rpc.registration.center.client.springboot.aop;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MockAspect {

  @Autowired
  private ObjectMapper objectMapper;

  @Around("within(com.wxxtest.springboot.aop.*) && @annotation(com.wxxtest.springboot.aop.Mock)")
  public Object mock(ProceedingJoinPoint pjp) throws Throwable {
    MethodSignature signature = (MethodSignature) pjp.getSignature();
    Method targetMethod = signature.getMethod();
    Mock mock = targetMethod.getAnnotation(Mock.class);
    String retValue = getValue(mock.resultKey());
    Class<?> returnType = targetMethod.getReturnType();
    return objectMapper.readValue(retValue, returnType);
  }

  // 这里可以和配置中心集成，根据key去拿配置中心中的配置值
  private String getValue(String key) {
    return "{}";
  }
}
