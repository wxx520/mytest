package com.wxxtest.algorithm.datastructure.stack;

import java.util.Stack;

/**
 * 有效的括号
 */
public class ValidParentheses {

  public boolean isValid(String s) {
    int len = s.length();
    if (len % 2 == 1) {
      return false;
    }
    Stack<Character> stack = new Stack<>();
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
