package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicate-letters/description/?envType=problem-list-v2&envId=00n6fGiz">316. 去除重复字母</a>
 */
public class RemoveDuplicateLetters {

  /**
   *
   */
  public String removeDuplicateLetters(String s) {
    int n = s.length();
    if (n < 2) {
      return s;
    }
    char[] charArray = s.toCharArray();
    int[] lastIndex = new int[26];
    for (int i = 0; i < n; i++) {
      lastIndex[charArray[i] - 'a'] = i;
    }

    Deque<Character> stack = new ArrayDeque<>();
    stack.add('a');
    // 栈中有的字符记录在这里
    boolean[] visited = new boolean[26];
    for (int i = 0; i < n; i++) {
      char c = charArray[i];
      if (visited[c - 'a']) {
        continue;
      }
      while (c < stack.peekLast() && lastIndex[stack.peekLast() - 'a'] > i) {
        char top = stack.removeLast();
        // 在出栈、入栈的时候，都需要维护 visited 数组的定义
        visited[top - 'a'] = false;
      }
      stack.addLast(c);
      // 在出栈、入栈的时候，都需要维护 visited 数组的定义
      visited[c - 'a'] = true;
    }
    StringBuilder sb = new StringBuilder();
    int ansLen = stack.size() - 1;
    for (int i = 0; i < ansLen; i++) {
      sb.insert(0, stack.removeLast());
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    RemoveDuplicateLetters r = new RemoveDuplicateLetters();
    System.out.println(r.removeDuplicateLetters("bcabc"));
    System.out.println(r.removeDuplicateLetters("cbacdcbc"));
  }
}
