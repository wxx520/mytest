package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/number-of-visible-people-in-a-queue/?envType=problem-list-v2&envId=cIOgJFon">1944. 队列中可以看到的人数</a>
 */
public class NumberOfVisiblePeopleInQueue {

  /**
   * 维护一个严格递减的单调栈,从右到左遍历身高数组(人们只关心右边的身高)
   */
  public int[] canSeePersonsCount(int[] heights) {
    int n = heights.length;
    if (n == 1) {
      return new int[]{0};
    }
    int[] ans = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(heights[n - 1]);
    for (int i = n - 2; i >= 0; i--) {
      int curH = heights[i];
      //比自己矮的数量
      while (!stack.isEmpty() && stack.peekLast() < curH) {
        ans[i]++;
        stack.removeLast();
      }
      //首个不矮于自己的情况
      if (!stack.isEmpty()) {
        if (stack.peekLast() == curH) {
          //遇到和自己一样的，只留下自己
          stack.removeLast();
        }
        ans[i]++;
      }
      stack.addLast(curH);
    }
    return ans;
  }

  public static void main(String[] args) {
    NumberOfVisiblePeopleInQueue n = new NumberOfVisiblePeopleInQueue();
    System.out.println(Arrays.toString(n.canSeePersonsCount(
            new int[]{10, 6, 8, 5, 11, 9}
    )));
    System.out.println(Arrays.toString(n.canSeePersonsCount(
            new int[]{5, 1, 2, 3, 10}
    )));
  }
}
