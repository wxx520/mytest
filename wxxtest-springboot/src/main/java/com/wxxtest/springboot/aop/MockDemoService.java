package com.wxxtest.rpc.registration.center.client.springboot.aop;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MockDemoService {

  @Mock(resultKey = "resultKey4f")
  public Object f() {
    return "MockDemoService.f()";
  }

  @Mock(resultKey = "resultKey4g")
  public Map<String, String> g() {
    return Map.of();
  }
}
