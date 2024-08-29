package com.wxxtest.algorithm.dp.lis;

public class MaximumSubarray {

  public int maxSubArray(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    return -1;
  }

  /**
   * 定义f[i]以i结尾的最大子数组和，则max(f[i])即为结果
   * f[i] = max(0,f[i-1])+nums[i];
   */
  public int maxSubArrayBDP(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    int[] f = new int[n];
    f[0] = nums[0];
    int ans = f[0];
    for (int i = 1; i < n; i++) {
      f[i] = Math.max(f[i - 1], 0) + nums[i];
      ans = Math.max(ans, f[i]);
    }
    return ans;
  }

  public static void main(String[] args) {
    MaximumSubarray ms = new MaximumSubarray();
    System.out.println(ms.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    System.out.println(ms.maxSubArray(new int[]{1}));
    System.out.println(ms.maxSubArray(new int[]{5, 4, -1, 7, 8}));

    System.out.println(ms.maxSubArrayBDP(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    System.out.println(ms.maxSubArrayBDP(new int[]{1}));
    System.out.println(ms.maxSubArrayBDP(new int[]{5, 4, -1, 7, 8}));
  }
}
