package com.wxxtest.rpc.framework.ratelimiting;

import com.wxxtest.rpc.framework.ratelimiting.CounterRLA;
import com.wxxtest.rpc.framework.ratelimiting.SlidingWindowCounterRLA;
import org.junit.Test;

public class CLATest {

  @Test
  public void counterCLA(){
    CounterRLA c1 = new CounterRLA();
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

    CounterRLA c1 = new CounterRLA(20);
    for (int i = 0; i < 25; i++) {
      System.out.println(c1.isLimited());
    }
  }

  @Test
  public void slidingWindowCLA(){
    SlidingWindowCounterRLA s = new SlidingWindowCounterRLA();
    for (int i = 0; i < 103; i++) {
      System.out.println(s.isLimited());
    }
  }

  @Test
  public void slidingWindowCLAWithMaxRateConfig(){
    SlidingWindowCounterRLA s = new SlidingWindowCounterRLA(102);
    for (int i = 0; i < 103; i++) {
      System.out.println(s.isLimited());
    }
  }
}
