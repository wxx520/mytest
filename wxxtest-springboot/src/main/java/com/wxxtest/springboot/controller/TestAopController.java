package com.wxxtest.rpc.registration.center.client.springboot.controller;


import com.wxxtest.rpc.registration.center.client.springboot.aop.MockDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestAopController {
  @Autowired
  private ApplicationContext applicationContext;

  @RequestMapping("testMock")
  public String testMock() {
    MockDemoService mockDemoService = applicationContext.getBean(
            MockDemoService.class);
    System.out.println("mockDemoService.f(): " + mockDemoService.f());
    System.out.println("mockDemoService.g(): " + mockDemoService.g());

    return "OK";
  }

}
