package com.wxxtest.rpc.framework.ratelimiting;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 计数时，
 * 将单位时间窗口，划分成更小的粒度，时间窗口按照更小的粒度滑动
 * 比如时间单位窗口为1min，划分为60个1s的窗口，对于1min56s进来的请求，
 * 计数时计算[57s,1min56s]这60个单位时间
 * 理论上当划分精度足够细时，就能解决固定窗口的问题，但是实现复杂
 */
public class SlidingWindowCounterRLA implements RateLimitingAlgorithm {
  /**
   * 单位时间划分的小周期（单位时间是1分钟，1s一个小格子窗口，一共60个格子）
   */
  private final int SUB_CYCLE = 1;

  /**
   * 每分钟限流请求数
   */
  private final int thresholdPerMin;

  /**
   * 计数器, k-为当前窗口的开始时间值秒，value为当前窗口的计数
   */
  private final Map<Long, Integer> counters = new TreeMap<>();

  public SlidingWindowCounterRLA() {
    thresholdPerMin = 100;
  }

  public SlidingWindowCounterRLA(int thresholdPerMin) {
    this.thresholdPerMin = thresholdPerMin;
  }

  @Override
  public synchronized boolean isLimited() {
    long currentWindowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / SUB_CYCLE; //获取当前时间在哪个小周期窗口
    int currentWindowNum = countCurrentWindow(currentWindowTime); //当前窗口总请求数
    //超过阀值限流
    if (currentWindowNum >= thresholdPerMin) {
      return true;
    }

    //计数器+1
    int c = counters.getOrDefault(currentWindowTime, 0);
    counters.put(currentWindowTime, c + 1);
    return false;
  }

  /**
   * 统计当前窗口的请求数
   */
  private int countCurrentWindow(long currentWindowTime) {
    //计算窗口开始位置
    long startTime = currentWindowTime - SUB_CYCLE * (60 / SUB_CYCLE - 1);
    int count = 0;

    //遍历存储的计数器
    Iterator<Map.Entry<Long, Integer>> iterator = counters.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Long, Integer> entry = iterator.next();
      // 删除无效过期的子窗口计数器
      if (entry.getKey() < startTime) {
        iterator.remove();
      } else {
        //累加当前窗口的所有计数器之和
        count = count + entry.getValue();
      }
    }
    return count;
  }

}
