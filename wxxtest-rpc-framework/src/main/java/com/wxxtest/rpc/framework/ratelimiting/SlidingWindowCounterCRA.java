package com.wxxtest.rpc.framework.ratelimiting;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SlidingWindowCounterCRA implements RateLimitingAlgorithm {
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

  public SlidingWindowCounterCRA() {
    thresholdPerMin = 100;
  }

  public SlidingWindowCounterCRA(int thresholdPerMin) {
    this.thresholdPerMin = thresholdPerMin;
  }

  @Override
  public synchronized boolean isLimited() {
    long currentWindowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / SUB_CYCLE * SUB_CYCLE; //获取当前时间在哪个小周期窗口
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
