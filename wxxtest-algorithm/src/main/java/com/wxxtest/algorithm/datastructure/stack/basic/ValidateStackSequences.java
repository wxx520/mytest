package com.wxxtest.algorithm.datastructure.stack.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/validate-stack-sequences/description/">946. 验证栈序列</a>
 */
public class ValidateStackSequences {

  /**
   * 模拟入栈和出栈的过程
   */
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    int len1 = pushed.length;
    int len2 = popped.length;

    if (len1 == 0 && len2 == 0) {
      return true;
    }

    if (len2 == 0 || len1 != len2) {
      return false;
    }

    int index = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    for (int push : pushed) {
      stack.push(push);
      while (!stack.isEmpty() && stack.peek() == popped[index]) {
        stack.pop();
        index++;
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    int[] pushed = {1, 2, 3, 4, 5};
    int[] popped = {4, 5, 3, 2, 1};
    ValidateStackSequences solution = new ValidateStackSequences();
    boolean res = solution.validateStackSequences(pushed, popped);
    System.out.println(res);
  }
}
