package com.wxxtest.algorithm.datastructure.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/">150. 逆波兰表达式求值</a>
 */
public class EvaluateReversePolishNotation {

  /**
   * 后进先出的特性
   * 遍历tokens，如果发现是数字，则入栈
   * 如果是操作符operator, 则有
   * right = stack.pop();
   * left = stack.pop();
   * stack.push(left operator right);
   */
  public int evalRPN(String[] tokens) {
    Deque<Integer> stack = new ArrayDeque<>();
    int left;
    int right;
    for (String t : tokens) {
      if (isNumber(t)) {
        stack.push(Integer.parseInt(t));
        continue;
      }
      right = stack.pop();
      left = stack.pop();
      switch (t) {
        case "+" -> stack.push(left + right);
        case "-" -> stack.push(left - right);
        case "*" -> stack.push(left * right);
        default -> stack.push(left / right);
      }
    }
    return stack.pop();
  }

  private boolean isNumber(String t) {
    return !("-".equals(t) || "+".equals(t) || "*".equals(t) || "/".equals(t));
  }

  public static void main(String[] args) {
    EvaluateReversePolishNotation n = new EvaluateReversePolishNotation();
    System.out.println(n.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    System.out.println(n.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    System.out.println(n.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
  }
}
