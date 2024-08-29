package com.wxxtest.algorithm.datastructure.queue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表，
 * 用字母 A 到 Z 表示，以及一个冷却时间 n。
 * 每个周期或时间间隔允许完成一项任务。
 * 任务可以按任何顺序完成，但有一个限制：
 * 两个 相同种类 的任务之间必须有长度为 n 的冷却时间。
 * 返回完成所有任务所需要的 最短时间间隔 。
 */
public class TaskScheduler {

  public int leastInterval(char[] tasks, int n) {
    if (n == 0) {
      return tasks.length;
    }

    int[] count = new int[26];
    for (char t : tasks) {
      count[t - 'A']++;
    }
    //最大堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(26, Comparator.reverseOrder());

    //把任务数大于0的任务加到最大堆中
    for (int c : count) {
      if (c > 0) {
        maxHeap.add(c);
      }
    }
    //依次拿出任务数最多的任务
    int res = 0;
    //临时队列
    Queue<Integer> queue = new LinkedList<>();
    while (!maxHeap.isEmpty()) {
      // 注意：这里是 n + 1，即在 n + 1 个时间单位内，不能有重复任务
      for (int i = 0; i <= n; i++) {
        if (!maxHeap.isEmpty()) {
          int front = maxHeap.poll();
          if (front > 1) {
            // 因为完成了一个任务，接下来还有 front - 1 次任务
            queue.add(front - 1);
          }
        }
        res++;
        if (maxHeap.isEmpty() && queue.isEmpty()) {
          break;
        }
      }
      while (!queue.isEmpty()) {
        maxHeap.offer(queue.poll());
      }
    }
    return res;
  }

  public static void main(String[] args) {
    TaskScheduler ts = new TaskScheduler();
    System.out.println(ts.leastInterval(
            new char[]{'A', 'A', 'A', 'B', 'B', 'B'},
            2
    ));
    System.out.println(ts.leastInterval(
            new char[]{'A', 'C', 'A', 'B', 'D', 'B'},
            1
    ));
    System.out.println(ts.leastInterval(
            new char[]{'A', 'A', 'A', 'B', 'B', 'B'},
            3
    ));
    System.out.println(ts.leastInterval(
            new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'},
            2
    ));
  }
}
