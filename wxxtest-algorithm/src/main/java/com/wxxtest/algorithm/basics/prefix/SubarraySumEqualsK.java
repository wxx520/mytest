package com.wxxtest.algorithm.basics.prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/count-number-of-nice-subarrays/description/?envType=problem-list-v2&envId=5F9Kf6K8">560. 和为 K 的子数组</a>
 */
public class SubarraySumEqualsK {

  public int subarraySum(int[] nums, int k) {
    // key：前缀和，value：key 对应的前缀和的个数
    Map<Integer, Integer> preSumFreq = new HashMap<>();
    //对于前缀和等于k的key其本身就是一种答案
    preSumFreq.put(0, 1);
    int preSum = 0;
    int count = 0;
    for (int num : nums) {
      preSum += num;
      //满足 preSum - (preSum - k) == k 的区间的个数是我们所关心的。
      Integer preCount = preSumFreq.get(preSum - k);
      if (preCount != null) {
        count += preCount;
      }
      preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
    }
    return count;
  }

  public int subarraySumByBF(int[] nums, int k) {
    int n = nums.length;
    int s;
    int ans = 0;
    for (int i = 0; i < n; i++) {
      s = 0;
      for (int j = i; j < n; j++) {
        s += nums[j];
        if (s == k) {
          ans++;
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    SubarraySumEqualsK k = new SubarraySumEqualsK();
    System.out.println(k.subarraySum(new int[]{1, 1, 1}, 2));
    System.out.println(k.subarraySum(new int[]{1, 2, 3}, 3));
    System.out.println(k.subarraySumByBF(new int[]{1, 1, 1}, 2));
    System.out.println(k.subarraySumByBF(new int[]{1, 2, 3}, 3));
  }
}
