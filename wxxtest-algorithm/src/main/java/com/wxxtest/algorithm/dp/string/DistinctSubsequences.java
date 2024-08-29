package com.wxxtest.algorithm.dp.string;

/**
 * <a href="https://leetcode.cn/problems/distinct-subsequences/description/?envType=study-plan-v2&envId=dynamic-programming">...</a>
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。
 */
public class DistinctSubsequences {

  /**
   * 滑动窗口
   */
  public static int numDistinct(String s, String t) {
    int m = s.length(), n = t.length();
    if (n >= m) {
      return s.equals(t) ? 1 : 0;
    }
    int index = -1;
    for (int i = 1; i < n; i++) {
      char tc = t.charAt(i);
      boolean find = false;
      for (int j = index + 1; j < m; j++) {
        if (s.charAt(j) == tc) {
          index = j;
          find = true;
          break;
        }
      }
      if (!find) {
        return 0;
      }
    }
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
      dp[i][n] = 1;
    }
    for (int i = m - 1; i >= 0; i--) {
      char sc = s.charAt(i);
      for (int j = n - 1; j >= 0; j--) {
        char tc = t.charAt(j);
        if (sc == tc) {
          dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
        } else {
          dp[i][j] = dp[i + 1][j];
        }

      }
    }
    return dp[0][0];
  }

  public static void main(String[] args) {
    System.out.println(numDistinct("rabbbit", "xrabbit"));
  }
}
