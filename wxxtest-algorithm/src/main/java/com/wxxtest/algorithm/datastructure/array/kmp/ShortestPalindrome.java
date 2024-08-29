package com.wxxtest.algorithm.datastructure.array.kmp;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为
 * 回文串。找到并返回可以用这种方式转换的最短回文串。
 * //TODO https://leetcode.cn/problems/shortest-palindrome/description/
 */
public class ShortestPalindrome {

  public String shortestPalindrome(String s) {
    StringBuilder l = new StringBuilder();
    StringBuilder r = new StringBuilder();
    int len = s.length();
    for (int i = len - 1; i >= 0; i--) {
      char ch = s.charAt(i);
      if (ch == s.charAt(len - i - 1)) {
        l.append(ch);
        r.append(ch);
        continue;
      }

    }
    r.reverse();
    l.append(r);
    return l.toString();
  }
}
