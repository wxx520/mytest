package com.wxxtest.algorithm.dp.string;

/**
 * <a href="https://leetcode.cn/problems/edit-distance/description/?envType=study-plan-v2&envId=dynamic-programming">...</a>
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class EditDistance {

  /**
   * 定义函数dp[i][j]表示word1的前i个字符，转换成word2的前j个字符需要的最少操作数，则由
   * 如果word1.charAt[i]==word2.charAt[j],
   * 则dp[i][j] = dp[i-1][j-1];
   * 否则，要么插入，要么删除，要么更新
   * 即dp[i][j] = min(dp[i-1][j],dp[i][j-1], dp[i-1][j-1])+1;
   *
   */
  public static int minDistance(String word1, String word2) {
    int n = word1.length(), m = word2.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = i + j;
        } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
        }
      }
    }
    return dp[n][m];
  }

  public static void main(String[] args) {
    System.out.println(minDistance("horse","ros"));
    System.out.println(minDistance("ros","ros"));
  }
}
