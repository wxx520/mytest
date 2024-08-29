package com.wxxtest.algorithm.dp.matrix;

/**
 * 每次只能向下或者向右移动一步。求最小路径和
 */
public class MinimumPathSum {

  /**
   * dp[i][j]表示到达(i,j)的最小路径和
   * 则有d[i][j] = grid[i][j]+min(d[i-1][j],d[i][j-1])
   * 要么上面下来，要么左边过来
   *
   * @param grid 礼物矩阵
   * @return 最小路径和
   */
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = grid[0][0];
    for (int i = 1; i < n; i++) {
      dp[0][i] = dp[0][i - 1] + grid[0][i];
    }

    for (int i = 1; i < m; i++) {
      dp[i][0] = dp[i - 1][0] + grid[i][0];
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
      }
    }
    return dp[m-1][n-1];
  }

  public static void main(String[] args) {
    MinimumPathSum test = new MinimumPathSum();
    System.out.println(test.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    System.out.println(test.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
  }

}
