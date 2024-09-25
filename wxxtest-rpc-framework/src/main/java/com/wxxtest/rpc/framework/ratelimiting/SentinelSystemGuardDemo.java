package com.wxxtest.rpc.registration.center.client.rpc.framework.ratelimiting;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.util.TimeUtil;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 系统自适应限流算法，根据以下系统规则定义请求是否过多：
 *   1. Load
 *   2. cpu usage
 *   3. RT(response time)
 *   4. 入口qps
 * <p>
 *   <a href="https://sentinelguard.io/zh-cn/docs/system-adaptive-protection.html">...</a>
 */
public class SentinelSystemGuardDemo {
  private static final AtomicInteger pass = new AtomicInteger();
  private static final AtomicInteger block = new AtomicInteger();
  private static final AtomicInteger total = new AtomicInteger();

  private static volatile boolean stop = false;
  private static final int THREAD_COUNT = 100;

  private static int seconds = 60 + 40;

  public static void main(String[] args) {

    tick();
    initSystemRule();

    for (int i = 0; i < THREAD_COUNT; i++) {
      Thread entryThread = new Thread(() -> {
        while (true) {
          Entry entry = null;
          try {
            entry = SphU.entry("methodA", EntryType.IN);
            pass.incrementAndGet();
            TimeUnit.MILLISECONDS.sleep(20);
          } catch (BlockException e1) {
            block.incrementAndGet();
            try {
              TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
              // ignore
            }
          } catch (Exception e2) {
            // biz exception
          } finally {
            total.incrementAndGet();
            if (entry != null) {
              entry.exit();
            }
          }
        }
      });
      entryThread.setName("working-thread");
      entryThread.start();
    }
  }

  private static void initSystemRule() {
    SystemRule rule = new SystemRule();
    // max load is 3
    rule.setHighestSystemLoad(3.0);
    // max cpu usage is 60%
    rule.setHighestCpuUsage(0.6);
    // max avg rt of all request is 10 ms
    rule.setAvgRt(10);
    // max total qps is 20
    rule.setQps(20);
    // max parallel working thread is 10
    rule.setMaxThread(10);

    SystemRuleManager.loadRules(Collections.singletonList(rule));
  }

  private static void tick() {
    Thread timer = new Thread(new TimerTask());
    timer.setName("sentinel-timer-task");
    timer.start();
  }

  static class TimerTask implements Runnable {
    @Override
    public void run() {
      System.out.println("begin to statistic!!!");
      long oldTotal = 0;
      long oldPass = 0;
      long oldBlock = 0;
      while (!stop) {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        long globalTotal = total.get();
        long oneSecondTotal = globalTotal - oldTotal;
        oldTotal = globalTotal;

        long globalPass = pass.get();
        long oneSecondPass = globalPass - oldPass;
        oldPass = globalPass;

        long globalBlock = block.get();
        long oneSecondBlock = globalBlock - oldBlock;
        oldBlock = globalBlock;

        System.out.println(seconds + ", " + TimeUtil.currentTimeMillis() + ", total:"
                + oneSecondTotal + ", pass:"
                + oneSecondPass + ", block:" + oneSecondBlock);
        if (seconds-- <= 0) {
          stop = true;
        }
      }
      System.exit(0);
    }
  }
}
