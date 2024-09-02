package com.wxxtest.algorithm.datastructure.stack.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 有效的括号
 * <a href="https://leetcode.cn/problems/valid-parentheses/description/">20. 有效的括号</a>
 */
public class ValidParentheses {

  public boolean isValid(String s) {
    int len = s.length();
    if (len % 2 == 1) {
      return false;
    }
    Deque<Character> stack = new ArrayDeque<>();
    for (Character c : s.toCharArray()) {
      if (c == '(') {
        stack.push(')');
      } else if (c == '[') {
        stack.push(']');
      } else if (c == '{') {
        stack.push('}');
      } else if (stack.isEmpty() || stack.pop() != c) {
        return false;
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    ValidParentheses vp = new ValidParentheses();
    System.out.println(vp.isValid("()"));
    System.out.println(vp.isValid("()[]{}"));
    System.out.println(vp.isValid("(]"));
  }
}
