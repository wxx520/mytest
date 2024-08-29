package com.wxxtest.algorithm.dp.string;

/**
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 * <a href="https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings/description/?envType=study-plan-v2&envId=dynamic-programming">...</a>
 */
public class MinimumASCIIDeleteSumForTwoStrings {

  /**
   * dp[i][j]表示s1的前i个字符与s2前j个字符相等所需要删除字符的最小ASCII码
   */
  public static int minimumDeleteSum(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 1; i <= m; i++) {
      dp[0][i] = dp[0][i - 1] + s2.codePointAt(i - 1);
    }
    for (int i = 1; i <= n; i++) {
      dp[i][0] = dp[i - 1][0] + s1.codePointAt(i - 1);
    }
    for (int i = 1; i <= n; i++) {
      int c1 = s1.codePointAt(i - 1);
      for (int j = 1; j <= m; j++) {
        int c2 = s2.codePointAt(j - 1);
        if (c1 == c2) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(Math.min(dp[i - 1][j] + c1, dp[i][j - 1] + c2), dp[i - 1][j - 1] + c1 + c2);
        }
      }
    }
    return dp[n][m];
  }

  public static int minimumDeleteSumByModel(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    //dp[i][j]表示si..sn与sj..sm的最小ASCII值和
    int[][] dp = new int[n + 1][m + 1];
    for (int i = n - 1; i >= 0; i--) {
      dp[i][m] = dp[i + 1][m] + s1.codePointAt(i);
    }
    for (int i = m - 1; i >= 0; i--) {
      dp[n][i] = dp[n][i + 1] + s2.codePointAt(i);
    }
    for (int i = n - 1; i >= 0; i--) {
      int c1 = s1.codePointAt(i);
      for (int j = m - 1; j >= 0; j--) {
        int c2 = s2.codePointAt(j);
        if (c1 == c2) {
          dp[i][j] = dp[i + 1][j + 1];
        } else {
          dp[i][j] = Math.min(dp[i + 1][j] + c1, dp[i][j + 1] + c2);
        }
      }
    }
    return dp[0][0];
  }

  public static void main(String[] args) {
    System.out.println(minimumDeleteSum("sea", "eat"));
    System.out.println(minimumDeleteSumByModel("sea", "eat"));
    System.out.println(minimumDeleteSum("delete", "leet"));
    System.out.println(minimumDeleteSumByModel("delete", "leet"));
  }
}
