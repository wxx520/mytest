package com.wxxtest.algorithm.dp.matrix;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、
 * 直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 */
public class GridMaxValueTest {

  /**
   * 假设dp[m][n]为mxn级礼物矩阵的最大礼物价值，则dp[m][n] = max(dp[m-1][n],d[m][n-1])+grid[m-1][n-1];
   *
   * @param grid
   * @return
   */
  public int maxValue(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
      return 0;
    }
    int mLen = grid.length;
    int nLen = grid[0].length;
    int[][] dp = new int[mLen][nLen];
    dp[0][0] = grid[0][0];
    for (int i = 1; i < mLen; i++) {
      dp[i][0] = grid[i][0] + dp[i - 1][0];
    }
    for (int j = 1; j < nLen; j++) {
      dp[0][j] = grid[0][j] + dp[0][j - 1];
    }
    for (int i = 1; i < grid.length; i++) {
      for (int j = 1; j < grid[i].length; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
      }
    }
    return dp[mLen - 1][nLen - 1];
  }

  public void t1() {
    int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    System.out.println(maxValue(grid));
  }
}
