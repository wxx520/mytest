package com.wxxtest.rpc.framework.ratelimiting;

import org.junit.Test;

public class CLATest {

  @Test
  public void counterCLA(){
    CounterRateLimitingAlgorithm c1 = new CounterRateLimitingAlgorithm();
    for (int i = 0; i < 200; i++) {
      System.out.println(c1.isLimited());
      if(i==120){
        try {
          Thread.sleep(50000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  @Test
  public void counterCLAWithMaxRateConfig(){

    CounterRateLimitingAlgorithm c1 = new CounterRateLimitingAlgorithm(20);
    for (int i = 0; i < 25; i++) {
      System.out.println(c1.isLimited());
    }
  }

  @Test
  public void slidingWindowCLA(){
    SlidingWindowCounterCRA s = new SlidingWindowCounterCRA();
    for (int i = 0; i < 103; i++) {
      System.out.println(s.isLimited());
    }
  }

  @Test
  public void slidingWindowCLAWithMaxRateConfig(){
    SlidingWindowCounterCRA s = new SlidingWindowCounterCRA(102);
    for (int i = 0; i < 103; i++) {
      System.out.println(s.isLimited());
    }
  }
}
