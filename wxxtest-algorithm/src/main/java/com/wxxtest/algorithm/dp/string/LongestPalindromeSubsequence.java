package com.wxxtest.algorithm.dp.string;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * <p>
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 */
public class LongestPalindromeSubsequence {
  /**
   * 若dp[i][j]表示si..sj之间的最长字符串，则有
   * 若si==sj,则dp[i][j]=dp[i+1][j-1]+2;
   * 否则，dp[i][j] = Max(dp[i+1][j],dp[i][j-1])
   * 若i==j,则dp[i][i]==1;
   * 若i>j,则dp[i][j]=0,只有j>i时，才有dp[i][j]>0成立
   */
  public static int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for (int i = n - 1; i >= 0; i--) {
      dp[i][i] = 1;
      char c1 = s.charAt(i);
      for (int j = i + 1; j < n; j++) {
        char c2 = s.charAt(j);
        if (c2 == c1) {
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[0][n - 1];
  }

  public static void main(String[] args) {
    System.out.println(longestPalindromeSubseq("bbabb"));
  }
}
