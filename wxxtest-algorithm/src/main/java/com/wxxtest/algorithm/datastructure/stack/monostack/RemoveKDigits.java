package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/remove-k-digits/">402. 移掉 K 位数字</a>
 */
public class RemoveKDigits {


  /**
   * 给定一个长度为 n 的数字序列D0D1D2D3…Dn−1
   * 从左往右找到第一个位置 i（i>0）使得 D[i]<D[i−1]并删去 D[i−1]
   * 如果不存在，说明整个数字序列单调不降，删去最后一个数字即可。
   * <p>
   * 时间复杂度为O(kn)
   * 使用栈将复杂度降低为O(n)
   */
  public String removeKdigits(String num, int k) {
    int n = num.length();
    if (k >= n) {
      return "0";
    }

    Deque<Character> deque = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      char digit = num.charAt(i);
      while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
        deque.pollLast();
        k--;
      }
      deque.offerLast(digit);
    }

    for (int i = 0; i < k; i++) {
      deque.removeLast();
    }

    StringBuilder ret = new StringBuilder();
    boolean leadingZero = true;
    while (!deque.isEmpty()) {
      char digit = deque.pollFirst();
      if (leadingZero && digit == '0') {
        continue;
      }
      leadingZero = false;
      ret.append(digit);
    }
    return ret.length() == 0 ? "0" : ret.toString();
  }


  public static void main(String[] args) {
    RemoveKDigits r = new RemoveKDigits();
    System.out.println(r.removeKdigits("112", 1));
    System.out.println(r.removeKdigits(
            "10001", 4
    ));
    System.out.println(r.removeKdigits(
            "1432219", 3
    ));
    System.out.println(r.removeKdigits(
            "10200", 1
    ));
    System.out.println(r.removeKdigits(
            "10", 2
    ));

    System.out.println(r.removeKdigits(
            "119", 1
    ));
    System.out.println(r.removeKdigits(
            "111111111", 3
    ));

  }
}
