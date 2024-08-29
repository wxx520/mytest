package com.wxxtest.algorithm.dp.lis;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 * <p>
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 */
public class LongestArithmeticSubsequenceOfGivenDifference {

  /**
   * 定义f(i),表示以第i个数为结尾的最长子序列，
   * 则对于f(i+1),若j in [0,i]中满足
   * nums[j] = nums[i+1]-diff,
   * 则有 f[i+1] = f[j]+1;
   * 否则 f[i+1] = 1;
   */
  public static int longestSubsequence(int[] arr, int difference) {
    int len = arr.length;
    if (len <= 1) {
      return len;
    }
    //<当前数，以当前数结尾的最长子序列>
    int ans = 1;
    Map<Integer, Integer> table = new HashMap<>();
    for (int v : arr) {
      int preV = v - difference;
      Integer preSeq = table.get(preV);
      if (preSeq == null) {
        table.put(v, 1);
      } else {
        table.put(v, preSeq + 1);
        ans = Math.max(ans, preSeq + 1);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(longestSubsequence(new int[]{},2));
  }
}
