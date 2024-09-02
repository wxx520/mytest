package com.wxxtest.algorithm.datastructure.stack.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/min-stack/description/">155. 最小栈</a>
 */
public class MinStack {

  private final Deque<Integer> data;

  //helper本质上是一个非严格的单调栈
  private final Deque<Integer> helper;

  public MinStack() {
    data = new ArrayDeque<>();
    helper = new ArrayDeque<>();
  }

  public void push(int val) {
    data.push(val);
    if (helper.isEmpty() || val <= helper.peek()) {
      helper.push(val);
    }
  }

  public void pop() {
    if (data.isEmpty()) {
      return;
    }
    int val = data.pop();
    if (val == helper.peek()) {
      helper.pop();
    }
  }

  public int top() {
    if (data.isEmpty()) {
      throw new RuntimeException("栈为空");
    }
    return data.peek();
  }

  public int getMin() {
    if (helper.isEmpty()) {
      throw new RuntimeException("栈为空");
    }
    return helper.peek();
  }

  public static void main(String[] args) {
    MinStack ms = new MinStack();
    ms.push(-2);
    ms.push(0);
    ms.push(-3);
    System.out.println(ms.getMin());
    ms.pop();
    System.out.println(ms.top());
    System.out.println(ms.getMin());
  }
}
