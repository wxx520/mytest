package com.wxxtest.algorithm.dp.lis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1027. 最长等差数列
 * <a href="https://leetcode.cn/problems/longest-arithmetic-subsequence/description/">...</a>
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
public class LongestArithmeticSubsequence {

  /**
   * 定义f(i,d), 表示数组中每个数都会和他之前的数组成不同等差d的数组，则
   * max(f(i,d)),其中0<=i<=len, d为每个数和他之前的数产生的差
   *
   */
  public static int longestArithSeqLengthByBruteForce(int[] nums) {
    int len = nums.length;
    if (len <= 2) {
      return len;
    }
    int ans = 2;
    Map<Integer, Integer> zeroDiffCount = new HashMap<>();
    //因为0每次等差后值不变，会被重复计算
    for (int v : nums) {
      Integer preCount = zeroDiffCount.get(v);
      if (preCount == null) {
        zeroDiffCount.put(v, 1);
      } else {
        zeroDiffCount.put(v, preCount + 1);
        ans = Math.max(ans, preCount + 1);
      }
    }
    //如果已经超过数组长度的一半，则不会有更长的子序列，直接返回
    if (ans > len / 2 + 1) {
      return ans;
    }

    //<等差diff，<以当前数结尾，以当前数结尾的最长等差子序列的长度>>
    Map<Integer, Map<Integer, Integer>> table = new HashMap<>();
    for (int i = 1; i < len; i++) {
      int curNum = nums[i];
      for (int j = i - 1; j >= 0; j--) {
        int diff = curNum - nums[j];
        //0会被重复计算
        if (diff == 0) {
          continue;
        }
        //该等差是否存在
        Map<Integer, Integer> theDiffSeqs = table.get(diff);
        //不存在则加入，并添加已当前数为结尾的最长子序列
        if (theDiffSeqs == null) {
          theDiffSeqs = new HashMap<>();
          theDiffSeqs.put(curNum, 2);
          table.put(diff, theDiffSeqs);
        } else {
          //存在也分两种情况
          Integer preCount = theDiffSeqs.get(nums[j]);
          //自己构成新的子序列
          if (preCount == null) {
            theDiffSeqs.put(curNum, 2);
          } else {
            //追加在之前已存在的后面
            theDiffSeqs.put(curNum, preCount + 1);
            ans = Math.max(ans, preCount + 1);
          }
        }
      }
    }
    return ans;
  }

  /**
   * 定义f(i,d)表示以第i个数结尾的形成等差为d的最长子序列
   * <p>
   * 2 <= nums.length <= 1000
   * 因为nums[i]的范围不大，可以用数组加快查询，否则可以用map可以加快
   * 0 <= nums[i] <= 500
   */
  public static int longestArithSeqLengthByDP(int[] nums) {
    int maxV = Arrays.stream(nums).max().getAsInt();
    int minV = Arrays.stream(nums).min().getAsInt();
    int diff = maxV - minV, ans = 2;
    for (int d = -diff; d <= diff; d++) {
      int[] f = new int[maxV + 1];
      Arrays.fill(f, -1);
      for (int v : nums) {
        int pre = v - d;
        if (pre >= minV && pre <= maxV && f[pre] != -1) {
          f[v] = Math.max(f[v], f[pre] + 1);
          ans = Math.max(ans, f[v]);
        }
        f[v] = Math.max(1, f[v]);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(longestArithSeqLengthByBruteForce(new int[]{}));
    System.out.println(longestArithSeqLengthByDP(new int[]{}));
  }
}
