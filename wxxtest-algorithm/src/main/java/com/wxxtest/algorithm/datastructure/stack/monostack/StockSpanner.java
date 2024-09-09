package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/online-stock-span/">901. 股票价格跨度</a>
 */
public class StockSpanner {

  /**
   * int[]{curPrice, spanner}
   */
  private Deque<int[]> stack;

  public StockSpanner() {
    stack = new ArrayDeque<>();
  }

  public int next(int price) {
    //当天价格包括自己至少跨度为1
    int curSpanner = 1;
    //和上一次的价格比较，若不小于则加上上一次的跨度
    while (!stack.isEmpty() && stack.peek()[0] <= price) {
      int[] prev = stack.pop();
      curSpanner += prev[1];
    }
    stack.push(new int[]{price, curSpanner});
    return curSpanner;
  }

  public static void main(String[] args) {
    StockSpanner ss = new StockSpanner();
    System.out.println(ss.next(100));
    System.out.println(ss.next(80));
    System.out.println(ss.next(60));
    System.out.println(ss.next(70));
    System.out.println(ss.next(60));
    System.out.println(ss.next(75));
    System.out.println(ss.next(85));
  }
}
