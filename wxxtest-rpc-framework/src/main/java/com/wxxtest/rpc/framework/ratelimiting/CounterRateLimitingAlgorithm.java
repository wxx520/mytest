package com.wxxtest.rpc.framework.ratelimiting;

public class CounterRateLimitingAlgorithm implements RateLimitingAlgorithm {

  private int maxRate = 10, count;

  private long currentTimeWindow;

  public CounterRateLimitingAlgorithm() {
    count = 0;
  }

  public CounterRateLimitingAlgorithm(int maxRate) {
    this.maxRate = maxRate;
    count = 0;
  }

  @Override
  public synchronized boolean isLimited() {
    long reqTime = System.currentTimeMillis();
    //是否在当前时间窗口里，不在则新起一个窗口，将当前时间窗口更新为当前请求的时间
    if (reqTime - currentTimeWindow > 1000) {
      currentTimeWindow = System.currentTimeMillis();
      count = 1;
      return false;
    }
    //如果已经到达阈值，新进来的请求肯定超了
    if(count>=maxRate){
      return true;
    }
    count++;
    return false;
  }
}
