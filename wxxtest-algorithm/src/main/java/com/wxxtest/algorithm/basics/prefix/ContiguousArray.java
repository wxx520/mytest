package com.wxxtest.algorithm.basics.prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/contiguous-array/description/?envType=problem-list-v2&envId=5F9Kf6K8">525. 连续数组</a>
 */
public class ContiguousArray {

  public int findMaxLength(int[] nums) {
    int n = nums.length;
    //key, 区间和，value区间的长度
    Map<Integer, Integer> preSumCount = new HashMap<>();
    preSumCount.put(0, -1);
    int count = 0;
    int res = 0;
    for (int i = 0; i < n; i++) {
      if (nums[i] == 1) {
        count++;
      } else {
        count--;
      }
      Integer preIndex = preSumCount.get(count);
      if (preIndex != null) {
        res = Math.max(res, i - preIndex);
      } else {
        preSumCount.put(count, i);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    ContiguousArray a = new ContiguousArray();
    System.out.println(a.findMaxLength(new int[]{0, 1}));
    System.out.println(a.findMaxLength(new int[]{0, 1, 0}));
  }
}
