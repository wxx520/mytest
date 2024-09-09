package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/daily-temperatures/">739. 每日温度</a>
 */
public class DailyTemperatures {

  /**
   * Stack<Integer> stack保存递增的温度的索引
   * n = t.len,ans = new int[n];
   * index表示遍历到,0<=i<n
   * while(!stack.isEmpty()&&t[stack.peek()]<t[i]){
   * int prevIndex = stack.pop();
   * ans[prevIndex] = i - prevIndex;
   * }
   * stack.push(i);
   * <p>
   * <a href="https://leetcode.cn/problems/daily-temperatures/solutions/48192/cheng-xu-yuan-de-zi-wo-xiu-yang-739-daily-temperat/">详细思路</a>
   */
  public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    if (n < 2) {
      return new int[n];
    }
    int[] ans = new int[n];
    //temperatures[stack.pop()]单调递减
    Deque<Integer> monoDecreaseStack = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      while (!monoDecreaseStack.isEmpty() && temperatures[monoDecreaseStack.peek()] < temperatures[i]) {
        int prevIndex = monoDecreaseStack.pop();
        ans[prevIndex] = i - prevIndex;
      }
      monoDecreaseStack.push(i);
    }
    return ans;
  }

  public static void main(String[] args) {
    DailyTemperatures dt = new DailyTemperatures();
    System.out.println(Arrays.toString(dt.dailyTemperatures(
            new int[]{73, 74, 75, 71, 69, 72, 76, 73}
    )));
    System.out.println(Arrays.toString(dt.dailyTemperatures(
            new int[]{30, 40, 50, 60}
    )));
    System.out.println(Arrays.toString(dt.dailyTemperatures(
            new int[]{30, 60, 90}
    )));
  }
}
