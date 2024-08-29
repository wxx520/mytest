package com.wxxtest.algorithm.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 */
public class MaxSlidingWindow {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (k <= 1) {
      return nums;
    }
    int len = nums.length;
    if (len <= k) {
      int max = nums[0];
      for (int i = 1; i < len; i++) {
        max = Math.max(nums[i], max);
      }
      return new int[]{max};
    }
    int resLen = len - (k - 1);
    int[] res = new int[resLen];
    int index = 0;

    // 滑动窗口，注意：保存的是索引值
    ArrayDeque<Integer> deque = new ArrayDeque<>(k);
    // 滑动窗口，注意：保存的是索引值
    for (int i = 0; i < len; i++) {
      // 当元素从左边界滑出的时候，如果它恰恰好是滑动窗口的最大值
      // 那么将它弹出
      if (i >= k && i - k == deque.getFirst()) {
        deque.pollFirst();
      }

      // 如果滑动窗口非空，新进来的数比队列里已经存在的数还要大
      // 则说明已经存在数一定不会是滑动窗口的最大值（它们毫无出头之日）
      // 将它们弹出
      while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
        deque.pollLast();
      }
      deque.add(i);
      // 队首一定是滑动窗口的最大值的索引
      if (i >= k - 1) {
        res[index++] = nums[deque.peekFirst()];
      }
    }
    return res;
  }

  public static void main(String[] args) {
    MaxSlidingWindow sw = new MaxSlidingWindow();
    System.out.println(Arrays.toString(sw.maxSlidingWindow(
            new int[]{
                    1, 3, -1, -3, 5, 3, 6, 7
            }, 3))
    );
    System.out.println(Arrays.toString(sw.maxSlidingWindow(
            new int[]{1}, 1))
    );
  }
}
