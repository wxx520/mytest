package com.wxxtest.algorithm.datastructure.queue;

import java.util.*;

/**
 * 给你一个下标从 0 开始的正整数数组 tasks ，表示需要 按顺序 完成的任务，
 * 其中 tasks[i] 表示第 i 件任务的 类型 。
 * 同时给你一个正整数 space ，表示一个任务完成 后 ，
 * 另一个 相同 类型任务完成前需要间隔的 最少 天数。
 * 在所有任务完成前的每一天，你都必须进行以下两种操作中的一种：
 * 1 完成 tasks 中的下一个任务
 * 2 休息一天
 * 请你返回完成所有任务所需的 最少 天数。
 * <a href="https://leetcode.cn/problems/task-scheduler-ii/">...</a>
 */
public class TaskSchedulerII {
  public long taskSchedulerII(int[] tasks, int space) {
    if (space == 0) {
      return tasks.length;
    }
    Map<Integer, Integer> freqMap = new HashMap<>();
    //统计每种任务出现的频次
    for (int t : tasks) {
      freqMap.put(t, freqMap.getOrDefault(t, 0) + 1);
    }

    //最大堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(freqMap.size(), Comparator.reverseOrder());
    for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
      maxHeap.offer(entry.getValue());
    }
    long res = 0;
    Queue<Integer> queue = new LinkedList<>();
    while (!maxHeap.isEmpty()) {
      // 注意：这里是 space + 1，即在 space + 1 个时间单位内，不能有重复任务
      for (int i = 0; i <= space; i++) {
        if (!maxHeap.isEmpty()) {
          int front = maxHeap.poll();
          if (front > 1) {
            queue.offer(front - 1);
          }
        }
        res++;
        if (maxHeap.isEmpty() && queue.isEmpty()) {
          break;
        }
      }
      while (!queue.isEmpty()){
        maxHeap.offer(queue.poll());
      }
    }
    return res;
  }

  public static void main(String[] args) {
    TaskSchedulerII ts2 = new TaskSchedulerII();
    System.out.println(ts2.taskSchedulerII(
                    new int[]{1, 2, 1, 2, 3, 1},
                    3
            )
    );
    System.out.println(ts2.taskSchedulerII(
                    new int[]{5, 8, 8, 5},
                    2
            )
    );
  }
}
