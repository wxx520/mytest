package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/132-pattern/">456. 132 模式</a>
 */
public class Pattern123 {

  /**
   * <a href="https://leetcode.cn/problems/132-pattern/solutions/676970/xiang-xin-ke-xue-xi-lie-xiang-jie-wei-he-95gt/">详细题解</a>
   */
  public boolean find132pattern(int[] nums) {
    int n = nums.length;
    if (n < 3) {
      return false;
    }
    Deque<Integer> stack = new ArrayDeque<>();
    //第二大的元素
    int num2 = Integer.MIN_VALUE;
    int curNum;
    for (int i = n - 1; i >= 0; i--) {
      curNum = nums[i];
      //此时curNum为n1,n1的下标最小
      if (curNum < num2) {
        return true;
      }
      //从右向左遍历，形成递减栈
      // 若发现其左边有比自己大的元素，则在递减的stack里找到最大的做为n2,则必有n2<n3,其中n3的下标比n2小
      while (!stack.isEmpty() && stack.peekLast() < curNum) {
        num2 = stack.removeLast();
      }
      stack.addLast(curNum);
    }
    return false;
  }

  public static void main(String[] args) {
    Pattern123 p = new Pattern123();
    System.out.println(p.find132pattern(new int[]{1, 2, 3, 4}));
    System.out.println(p.find132pattern(new int[]{3, 1, 4, 2}));
    System.out.println(p.find132pattern(new int[]{-1, 3, 2, 0}));
    System.out.println(p.find132pattern(new int[]{3, 5, 0, 3, 4}));
    System.out.println(p.find132pattern(new int[]{1, 4, 0, -1, -2, -3, -1, -2}));

  }
}
