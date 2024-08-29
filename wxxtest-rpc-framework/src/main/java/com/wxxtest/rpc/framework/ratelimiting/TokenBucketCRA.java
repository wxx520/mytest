package com.wxxtest.rpc.framework.ratelimiting;

public class TokenBucketCRA implements RateLimitingAlgorithm{

  private final int capacity;     // 令牌桶容量
  private final int rate;         // 令牌生成速率，单位：令牌/秒
  private int tokens;             // 当前令牌数量
  private long lastRefillTimestamp;  // 上次令牌生成时间戳

  /**
   * 构造函数中传入令牌桶的容量和令牌生成速率。
   * @param capacity 令牌桶的容量
   * @param rate 每秒钟放入令牌的速度
   */
  public TokenBucketCRA(int capacity, int rate) {
    this.capacity = capacity;
    this.rate = rate;
    this.tokens = capacity;
    this.lastRefillTimestamp = System.currentTimeMillis();
  }
  @Override
  public synchronized boolean isLimited() {
    refill();
    if (tokens > 0) {
      tokens--;
      return false;
    } else {
      return true;
    }
  }

  /**
   * refill() 方法用于生成令牌，其中计算令牌数量的逻辑是按照令牌生成速率每秒钟生成一定数量的令牌，
   * tokens 变量表示当前令牌数量，
   * lastRefillTimestamp 变量表示上次令牌生成的时间戳。
   */
  private void refill() {
    long now = System.currentTimeMillis();
    if (now > lastRefillTimestamp) {
      int generatedTokens = (int) ((now - lastRefillTimestamp) / 1000 * rate);
      tokens = Math.min(tokens + generatedTokens, capacity);
      lastRefillTimestamp = now;
    }
  }
}
