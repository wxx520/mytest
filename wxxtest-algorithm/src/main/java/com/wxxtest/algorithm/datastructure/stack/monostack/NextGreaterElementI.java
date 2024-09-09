package com.wxxtest.algorithm.datastructure.stack.monostack;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/next-greater-element-i/description/">496. 下一个更大元素 I</a>
 */
public class NextGreaterElementI {

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int n = nums2.length;
    if (n < 2) {
      return new int[]{-1};
    }
    //<值，下一个更大的值>
    Map<Integer, Integer> map = new HashMap<>();

    Deque<Integer> stack = new ArrayDeque<>();
    for (int curNum : nums2) {
      while (!stack.isEmpty() && stack.peek() < curNum) {
        map.put(stack.pop(), curNum);
      }
      stack.push(curNum);
    }
    //选出nums1中每个元素的下一个最大值
    int m = nums1.length;
    int[] res = new int[m];
    for (int i = 0; i < m; i++) {
      res[i] = map.getOrDefault(nums1[i], -1);
    }
    return res;
  }

  public static void main(String[] args) {
    NextGreaterElementI n1 = new NextGreaterElementI();
    System.out.println(Arrays.toString(n1.nextGreaterElement(
                    new int[]{4, 1, 2},
                    new int[]{1, 3, 4, 2}
            )
    ));
    System.out.println(Arrays.toString(n1.nextGreaterElement(
                    new int[]{2, 4},
                    new int[]{1, 2, 3, 4}
            )
    ));
  }
}
