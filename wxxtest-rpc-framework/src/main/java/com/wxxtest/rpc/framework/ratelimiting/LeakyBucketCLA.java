package com.wxxtest.rpc.framework.ratelimiting;

public class LeakyBucketCLA implements RateLimitingAlgorithm {

  private final long capacity;    // 桶的容量
  private final long rate;        // 漏桶每秒钟的出水速率
  private long water;             // 当前桶中的水量
  private long lastLeakTimestamp; // 上次漏水时间戳

  public LeakyBucketCLA(long capacity, long rate) {
    this.capacity = capacity;
    this.rate = rate;
    this.water = 0;
    this.lastLeakTimestamp = System.currentTimeMillis();
  }

  @Override
  public synchronized boolean isLimited() {
    leak();
    if (water < capacity) {
      water++;
      return false;
    } else {
      return true;
    }
  }

  /**
   * leak() 方法用于漏水，根据当前时间和上次漏水时间戳计算出应该漏出的水量，然后更新桶中的水量和漏水时间戳等状态。
   */
  private void leak() {
    long now = System.currentTimeMillis();
    long elapsedTime = now - lastLeakTimestamp;
    long leakedWater = elapsedTime * rate / 1000;
    if (leakedWater > 0) {
      water = Math.max(0, water - leakedWater);
      lastLeakTimestamp = now;
    }
  }
}
