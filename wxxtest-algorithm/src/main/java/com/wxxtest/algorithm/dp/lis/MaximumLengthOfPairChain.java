package com.wxxtest.algorithm.dp.lis;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/maximum-length-of-pair-chain/description/?envType=study-plan-v2&envId=dynamic-programming">...</a>
 * 给你一个由 n 个数对组成的数对数组 pairs ，其中 pairs[i] = [left, right] 且 left < right 。
 * <p>
 * 现在，我们定义一种 跟随 关系，当且仅当 b < c 时，数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面。我们用这种形式来构造 数对链 。
 * <p>
 * 找出并返回能够形成的 最长数对链的长度 。
 * <p>
 * 你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 */
public class MaximumLengthOfPairChain {

  /**
   * 要想对数链条最长，就要尽量扩散的慢一点，
   *
   */
  public static int findLongestChainByDP(int[][] pairs) {
    int n = pairs.length;
    if (n <= 1) {
      return n;
    }
    //按照做边界从小打到排序
    Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
    //以第i个数字对做为结尾的数字链最长长度
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      int[] curPair = pairs[i];
      for (int j = 0; j < i; j++) {
        if (curPair[0] > pairs[j][1]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
    return dp[n - 1];
  }

  /**
   * 要想对数链条最长，就要尽量扩散的慢一点
   */
  public static int findLongestChainByGreedy(int[][] pairs) {
    //有限选择第二数更小的数对，为接下来的链路留下更多的空间，curr为前一个数对的第二个数
    int ans = 0, curr = Integer.MIN_VALUE;
    Arrays.sort(pairs, (a, b) -> ((a[1] - b[1] == 0) ? (a[0] - b[0]) : (a[1] - b[1])));
    for (int[] c : pairs) {
      if (curr < c[0]) {
        curr = c[1];
        ans++;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(findLongestChainByDP(new int[][]{{}}));
    System.out.println(findLongestChainByGreedy(new int[][]{{}}));
  }
}
