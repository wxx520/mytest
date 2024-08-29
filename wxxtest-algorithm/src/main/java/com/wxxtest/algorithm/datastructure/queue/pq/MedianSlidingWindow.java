package com.wxxtest.algorithm.datastructure.queue.pq;

import java.util.*;

public class MedianSlidingWindow {
  public double[] medianSlidingWindow(int[] nums, int k) {
    int len = nums.length;
    int resLen = len - (k - 1);
    double[] res = new double[resLen];
    if (k == 1) {
      for (int i = 0; i < len; i++) {
        res[i] = nums[i];
      }
      return res;
    }
    DualHeap heap = new DualHeap(k);
    for (int i = 0; i < k; i++) {
      heap.insert(nums[i]);
    }
    res[0] = heap.getMedian();
    int index = 1;

    for (int i = k; i < len; i++) {
      heap.insert(nums[i]);
      heap.erase(nums[i - k]);
      res[index++] = heap.getMedian();
    }
    return res;
  }

  public static void main(String[] args) {
    MedianSlidingWindow msw = new MedianSlidingWindow();
    System.out.println(Arrays.toString(msw.medianSlidingWindow(
            new int[]{1, 3, -1, -3, 5, 3, 6, 7},
            3
    )));
    System.out.println(Arrays.toString(msw.medianSlidingWindow(
            new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2},
            3
    )));

    System.out.println(Arrays.toString(msw.medianSlidingWindow(
            new int[]{-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648},
            3
    )));
  }
}

class DualHeap {
  // 大根堆，维护较小的一半元素
  private final PriorityQueue<Integer> small;
  // 小根堆，维护较大的一半元素
  private final PriorityQueue<Integer> large;

  private final int k;
  // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
  private int smallSize;
  private int largeSize;

  // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
  //直接删除的话么次删除的时间代价是logK且需要对其他元素做heapify，k为队列的大小
  private final Map<Integer, Integer> delayed;

  public DualHeap(int k) {
    this.k = k;
    this.small = new PriorityQueue<>(Comparator.reverseOrder());
    this.large = new PriorityQueue<>(Comparator.naturalOrder());
    smallSize = largeSize = 0;
    this.delayed = new HashMap<>();
  }

  public double getMedian() {
    return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
  }

  public void insert(int num) {
    if (small.isEmpty() || num <= small.peek()) {
      small.offer(num);
      ++smallSize;
    } else {
      large.offer(num);
      ++largeSize;
    }
    makeBalance();
  }

  /**
   * 始终满足
   * 1、0<=smallSize-largeSize<=1
   * 2、small.peek()<=large.peek()
   */
  private void makeBalance() {
    if (smallSize > largeSize + 1) {
      large.offer(small.poll());
      --smallSize;
      ++largeSize;

      //small的栈顶变了，需要prune，保证栈顶的有效性
      prune(small);
    } else if (smallSize < largeSize) {
      small.offer(large.poll());
      --largeSize;
      ++smallSize;

      //large的栈顶变了，需要prune，保证栈顶的有效性
      prune(large);
    }
  }

  private void prune(PriorityQueue<Integer> heap) {
    while (!heap.isEmpty()) {
      int num = heap.peek();
      Integer delFreq = delayed.get(num);
      if (delFreq == null) {
        break;
      }
      if (delFreq == 1) {
        delayed.remove(num);
      } else {
        delayed.put(num, --delFreq);
      }
      heap.poll();
    }
  }

  public void erase(int num) {
    delayed.put(num, delayed.getOrDefault(num, 0) + 1);
    if (num <= small.peek()) {
      smallSize--;
      if (small.peek() == num) {
        prune(small);
      }
    } else {
      largeSize--;
      if (large.peek() == num) {
        prune(large);
      }
    }
    makeBalance();
  }
}

