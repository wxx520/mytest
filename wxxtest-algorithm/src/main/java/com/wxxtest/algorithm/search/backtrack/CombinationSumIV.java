package com.wxxtest.algorithm.search.backtrack;

import java.util.Arrays;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 */
public class CombinationSumIV {

    private int ans, len;
    private int[] nums;


    public int combinationSum4(int[] nums, int target) {
      Arrays.sort(nums);
      ans = 0;
      len = nums.length;
      this.nums = nums;
      dfs(target, 0, Integer.MAX_VALUE);
      return ans;
    }

    private void dfs(int target, int sum, int lastNum) {
      if (sum == target) {
        ans++;
        return;
      }
      for (int i = 0; i < len; i++) {
        int cNum = nums[i];
        if (cNum + sum > target) {
          break;
        }
        dfs(target, sum + cNum, cNum);
      }
    }

  public int combinationSum4ByDP(int[] nums, int target) {
    //f[i]有nums中元素组成和为i的组合个数
    int[] f = new int[target + 1];
    f[0] = 1;
    for (int i = 1; i <= target; i++) {
      for (int num : nums) {
        if (num <= i) {
          f[i] += f[i - num];
        }
      }
    }
    return f[target];
  }

  public static void main(String[] args) {
    CombinationSumIV cs4 = new CombinationSumIV();
    System.out.println(cs4.combinationSum4(
            new int[]{1, 2, 3},
            4
    ));
    System.out.println(cs4.combinationSum4ByDP(
            new int[]{1, 2, 3},
            4
    ));
    System.out.println(cs4.combinationSum4ByDP(
            new int[]{3},
            9
    ));
    System.out.println(cs4.combinationSum4(
            new int[]{3},
            9
    ));
    System.out.println(cs4.combinationSum4(
            new int[]{2,1,3},
            35
    ));
    System.out.println(cs4.combinationSum4ByDP(
            new int[]{2,1,3},
            35
    ));
  }
}
