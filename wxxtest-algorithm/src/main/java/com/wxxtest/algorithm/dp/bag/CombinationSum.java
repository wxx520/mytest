package com.wxxtest.algorithm.dp.bag;

import java.util.Arrays;

public class CombinationSum {
  public int combinationSum4(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    Arrays.sort(nums);
    for (int i = 1; i <= target; i++) {
      for (int num : nums) {
        if (num > i) {
          break;
        }
        dp[i] += dp[i - num];
      }
    }
    return dp[target];
  }

  public static void main(String[] args) {
    CombinationSum cs = new CombinationSum();
    System.out.println(cs.combinationSum4(new int[]{1, 2, 3}, 4));
    System.out.println(cs.combinationSum4(new int[]{9}, 3));
  }
}
