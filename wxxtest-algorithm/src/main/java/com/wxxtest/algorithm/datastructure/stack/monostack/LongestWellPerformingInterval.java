package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/longest-well-performing-interval/description/?envType=problem-list-v2&envId=00n6fGiz">...</a>
 */
public class LongestWellPerformingInterval {

  /**
   * 前缀和
   * <a href="https://leetcode.cn/problems/longest-well-performing-interval/solutions/2110211/liang-chong-zuo-fa-liang-zhang-tu-miao-d-hysl/?envType=problem-list-v2&envId=00n6fGiz">...</a>
   */
  public int longestWPI(int[] hours) {
    int n = hours.length;
    int ans = 0;
    int[] s = new int[n + 1];
    Deque<Integer> deque = new ArrayDeque<>();
    deque.addLast(0);
    for (int j = 1; j <= n; j++) {
      int h = hours[j - 1];
      s[j] = s[j - 1] + (h > 8 ? 1 : -1);
      //感兴趣的j
      if (s[j] < s[deque.peekLast()]) {
        deque.addLast(j);
      }
    }
    //s始终升序,即天天加班
    if (deque.size() == 1) {
      return n;
    }

    int curS;
    for (int i = n; i > 0; i--) {
      curS = s[i];
      while (!deque.isEmpty() && curS > s[deque.peekLast()]) {
        ans = Math.max(ans, i - deque.removeLast()); // [栈顶,i) 可能是最长子数组
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    LongestWellPerformingInterval l = new LongestWellPerformingInterval();
    System.out.println(l.longestWPI(new int[]{9, 9, 6, 0, 6, 6, 9}));
    System.out.println(l.longestWPI(new int[]{6, 6, 6}));
  }
}
