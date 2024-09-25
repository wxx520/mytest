package com.wxxtest.algorithm.basics.prefix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/continuous-subarray-sum/?envType=problem-list-v2&envId=5F9Kf6K8">23. 连续的子数组和</a>
 */
public class ContinuousSubarraySum {

  public boolean checkSubarraySum(int[] nums, int k) {
    int n = nums.length;
    if (n <= 1) {
      return false;
    }
    if (k <= 1) {
      return true;
    }
    //找到一个对k取模结果一样的子数组，且两个子数组只差的长度>=2
    //key 前缀和， value，下标索引
    Map<Integer, Integer> preSumIndex = new HashMap<>();
    preSumIndex.put(0, -1);
    int preSum = 0;
    for (int i = 0; i < n; i++) {
      preSum += nums[i];
      int remainder = (preSum % k);
      Integer preIndex = preSumIndex.get(remainder);
      if (preIndex != null) {
        if (i - preIndex >= 2) {
          return true;
        }
      } else {
        preSumIndex.put(remainder, i);
      }
    }
    return false;
  }

  public boolean checkSubarraySum1(int[] nums, int k) {
    int n = nums.length;
    if (n <= 1) {
      return false;
    }
    if (k <= 1) {
      return true;
    }
    //找到一个对k取模结果一样的子数组，且两个子数组只差的长度>=2
    //i 对k取模的结果， value下标索引
    int[] remainders = new int[k];
    Arrays.fill(remainders, n);
    remainders[0] = -1;
    int preSum = 0;
    for (int i = 0; i < n; i++) {
      preSum += nums[i];
      int remainder = (preSum % k);
      if (remainders[remainder] == n) {
        remainders[remainder] = i;
      } else if (i - remainders[remainder] >= 2) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    ContinuousSubarraySum s = new ContinuousSubarraySum();
    System.out.println(s.checkSubarraySum(new int[]{5, 0, 0, 0, 0}, 3));
    System.out.println(s.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
    System.out.println(s.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));
    System.out.println(s.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13));

    System.out.println(s.checkSubarraySum1(new int[]{5, 0, 0, 0, 0}, 3));
    System.out.println(s.checkSubarraySum1(new int[]{23, 2, 4, 6, 7}, 6));
    System.out.println(s.checkSubarraySum1(new int[]{23, 2, 6, 4, 7}, 6));
    System.out.println(s.checkSubarraySum1(new int[]{23, 2, 6, 4, 7}, 13));
  }
}
