package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/find-the-most-competitive-subsequence/description/?envType=problem-list-v2&envId=cIOgJFon">1673. 找出最具竞争力的子序列</a>
 */
public class MostCompetitiveSubsequence {

  public int[] mostCompetitive(int[] nums, int k) {
    int n = nums.length;
    if (k >= n) {
      return nums;
    }
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      //删除前栈中至少需要保留的数量
      int remain = k - (n - i);
      int curNum = nums[i];
      while (deque.size() > remain && !deque.isEmpty() && nums[deque.peekLast()] > curNum) {
        deque.removeLast();
      }
      deque.addLast(i);
    }

    int[] ans = new int[k];
    for (int i = 0; i < k; i++) {
      ans[i] = nums[deque.removeFirst()];
    }
    return ans;
  }

  /**
   * 直接使用返回数组作为栈。
   */
  public int[] mostCompetitiveByArray(int[] nums, int k) {
    int n = nums.length;
    if (k >= n) {
      return nums;
    }
    int[] stack = new int[k];
    int stackSize = 0;
    for (int i = 0; i < n; i++) {
      while (stackSize + (n - i) > k && stackSize != 0 && stack[stackSize - 1] > nums[i]) {
        stackSize--;
      }
      if (stackSize < k) {
        stack[stackSize++] = nums[i];
      }
    }
    return stack;
  }

  public static void main(String[] args) {
    MostCompetitiveSubsequence se = new MostCompetitiveSubsequence();
    System.out.println(Arrays.toString(se.mostCompetitive(
            new int[]{3, 5, 2, 6, 2}, 2
    )));
    System.out.println(Arrays.toString(se.mostCompetitive(
            new int[]{2, 4, 3, 3, 5, 4, 9, 6}, 4
    )));
    System.out.println(Arrays.toString(se.mostCompetitiveByArray(
            new int[]{2, 4, 3, 3, 5, 4, 9, 6}, 4
    )));
  }
}
