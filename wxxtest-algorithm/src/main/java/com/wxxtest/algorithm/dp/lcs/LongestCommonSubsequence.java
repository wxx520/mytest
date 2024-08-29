package com.wxxtest.algorithm.dp.lcs;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 */
public class LongestCommonSubsequence {

  /**
   * f[i][j]表示t1前i个字符,t2的前j个字符的最长公共子序列
   * 则f[0][j] = 0, f[i][0] = 0, 0<=i<=n, 0<=j<=m;
   * 则由如果
   * f[i][j]=
   * 1、f[i-1]f[j-1]+1,t1.charAt(i)==t2.charAt(j)
   * 2、max(f[i-1][j], f[i][j-1]),t1.charAt(i)!=t2.charAt(j)
   *
   * @param text1 字符串1
   * @param text2 字符串2
   * @return 最长公共子序列的长度
   */
  public static int longestCommonSubsequence(String text1, String text2) {
    int n = text1.length(), m = text2.length();
    //f[i][j]表示t1前i个字符,t2的前j个字符的最长公共子序列
    int[][] f = new int[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      char c1 = text1.charAt(i - 1);
      for (int j = 1; j <= m; j++) {
        if (c1 == text2.charAt(j - 1)) {
          f[i][j] = f[i - 1][j - 1] + 1;
        } else {
          f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
        }
      }
    }
    return f[n][m];
  }

  public static void main(String[] args) {
    System.out.println(longestCommonSubsequence("abcde", "ace"));
  }
}
