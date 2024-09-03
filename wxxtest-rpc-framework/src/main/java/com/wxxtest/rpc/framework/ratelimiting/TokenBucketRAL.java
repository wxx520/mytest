package com.wxxtest.rpc.framework.ratelimiting;

/**
 * 令牌桶限流算法
 * 按照固定的速度向桶里放令牌
 * 来一个流量就拿走一个令牌，桶里有令牌，请求才能到达服务器
 * 真正的平缓限速的限流算法
 * 对于短时间激增的请求，即使单位时间没有到达限速的阈值，还是会限制掉
 */
public class TokenBucketRAL implements RateLimitingAlgorithm{

  private final int capacity;     // 令牌桶容量
  private final int rate;         // 令牌生成速率，单位：令牌/秒
  private int tokens;             // 当前令牌数量
  private long lastRefillTimestamp;  // 上次令牌生成时间戳

  /**
   * 构造函数中传入令牌桶的容量和令牌生成速率。
   * @param capacity 令牌桶的容量
   * @param rate 每秒钟放入令牌的速度
   */
  public TokenBucketRAL(int capacity, int rate) {
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
