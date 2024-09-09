package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/maximum-width-ramp/description/?envType=problem-list-v2&envId=00n6fGiz">962. 最大宽度坡</a>
 */
public class MaximumWidthRamp {

  /**
   * O(N^N)
   */
  public int maxWidthRampBF(int[] nums) {
    int n = nums.length;
    int ans = 0;
    for (int i = n - 1; i >= 0; i--) {
      int cur = nums[i];
      for (int j = 0; j < i; j++) {
        if (nums[j] <= cur) {
          ans = Math.max(ans, i - j);
          break;
        }
      }
      if (i == ans) {
        break;
      }
    }
    return ans;
  }

  public int maxWidthRamp(int[] nums) {
    int n = nums.length;
    Deque<Integer> monoStack = new ArrayDeque<>();
    monoStack.addLast(0);
    //左边元素尽量小，i尽量小
    for (int i = 1; i < n; i++) {
      if (nums[i] <= nums[monoStack.peekLast()]) {
        monoStack.addLast(i);
      }
    }
    int ans = 0;
    //j尽量大
    for (int i = n - 1; i >= 0; i--) {
      while (!monoStack.isEmpty() && nums[i] >= nums[monoStack.peekLast()]) {
        ans = Math.max(ans, i - monoStack.removeLast());
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    MaximumWidthRamp m = new MaximumWidthRamp();
    System.out.println(m.maxWidthRampBF(new int[]{6, 0, 8, 2, 1, 5}));
    System.out.println(m.maxWidthRampBF(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}));
    System.out.println(m.maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}));
  }

}
