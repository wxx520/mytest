package com.wxxtest.rpc.registration.center.client.rpc.framework.ratelimiting;

/**
 * 计数时，
 * 首先计算是否在同一个时间窗口中，不在则新起一个窗口计数，且肯定不会超出阈值；
 * 在则进行累积计数，根据是否超出阈值返回
 * 因此该算法无法处理：
 * 1、同一个时间窗口流量激增的情况，如每秒不超过1000，但是前200ms已经到达999
 * 2、跨时间窗口超速，0.5s到1s请求950，1s到1.5s500
 */
public class CounterRLA implements RateLimitingAlgorithm {

  private int maxRate = 10;
  private int count;

  private long currentTimeWindow;

  public CounterRLA() {
    count = 0;
  }

  public CounterRLA(int maxRate) {
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
    if (count >= maxRate) {
      return true;
    }
    count++;
    return false;
  }
}
