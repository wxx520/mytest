package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/description/">1081. 不同字符的最小子序列</a>
 */
public class SmallestSubsequenceOfDistinctCharacters {

  /**
   * 后遍历到的先删除，符合栈后进先出的特性
   */
  public String smallestSubsequence(String s) {
    int n = s.length();
    if (n < 2) {
      return s;
    }
    int[] lastIndex = new int[26];
    char[] charArray = s.toCharArray();
    for (int i = 0; i < n; i++) {
      lastIndex[charArray[i] - 'a'] = i;
    }

    boolean[] visited = new boolean[26];
    Deque<Character> stack = new ArrayDeque<>();
    //放入一个最小的字符永远不会被取出，做为哨兵，减去stack的判空操作
    stack.add('a');
    for (int i = 0; i < n; i++) {
      char ch = charArray[i];
      int chIndex = ch - 'a';
      if (visited[chIndex]) {
        continue;
      }
      while (stack.peekLast() > ch && lastIndex[stack.peekLast() - 'a'] > i) {
        visited[stack.removeLast() - 'a'] = false;
      }
      stack.addLast(ch);
      visited[chIndex] = true;

    }
    StringBuilder sb = new StringBuilder();
    //第一次放进去的为了减少判空哨兵不能放进去
    int ansLen = stack.size() - 1;
    for (int i = 0; i < ansLen; i++) {
      sb.insert(0, stack.removeLast());
    }
    return sb.toString();
  }

  public String smallestSubsequenceByStringBuilder(String s) {
    int n = s.length();
    if (n < 2) {
      return s;
    }
    int[] count = new int[26];
    char[] charArray = s.toCharArray();
    for (int i = 0; i < n; i++) {
      count[charArray[i] - 'a']++;
    }

    StringBuilder sb = new StringBuilder();
    boolean[] visited = new boolean[26];
    for (int i = 0; i < n; i++) {
      char ch = charArray[i];
      int chIndex = ch - 'a';
      if (visited[chIndex]) {
        count[chIndex]--;
        continue;
      }
      //当sb的最后一个元素比ch大，且后面还会出现，则删除最后一个元素
      while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch && count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
        visited[sb.charAt(sb.length() - 1) - 'a'] = false;
        sb.deleteCharAt(sb.length() - 1);
      }
      sb.append(ch);
      visited[chIndex] = true;
      count[chIndex]--;
    }
    return sb.toString();

  }

  public static void main(String[] args) {
    SmallestSubsequenceOfDistinctCharacters r = new SmallestSubsequenceOfDistinctCharacters();
    System.out.println(r.smallestSubsequence("bcabc"));
    System.out.println(r.smallestSubsequenceByStringBuilder("bcabc"));
    System.out.println(r.smallestSubsequence("cbacdcbc"));
    System.out.println(r.smallestSubsequenceByStringBuilder("cbacdcbc"));
  }

}
