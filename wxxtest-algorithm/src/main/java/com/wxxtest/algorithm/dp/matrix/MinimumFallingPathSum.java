package com.wxxtest.algorithm.dp.matrix;

/**
 * 给你一个 n x n 的 方形 整数数组 matrix ，
 * 请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径
 * 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列
 * （即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是
 * (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 */
public class MinimumFallingPathSum {

  /**
   * 和三角形的最小路径和思路一致，区别是走的方式多了一种
   *
   * @param matrix
   * @return
   */
  public int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;
    if (n == 1) {
      return matrix[0][0];
    }
    //f[i][j]为到达[i][j]的最小下降路径
    //f[i][j] = matrix[i][j]+min(f[i-1][j-1],f[i-1][j],f[i-1][j+1])
    int[][] f = new int[n][n];
    //边界情况1：第一行
    for (int i = 0; i < n; i++) {
      f[0][i] = matrix[0][i];
    }

    for (int i = 1; i < n; i++) {
      //边界情况2：其他行的第一个元素和最后一个元素
      f[i][0] = matrix[i][0] +
              Math.min(f[i - 1][0], f[i - 1][1]);
      f[i][n - 1] = matrix[i][n - 1] +
              Math.min(f[i - 1][n - 1], f[i - 1][n - 2]);
      for (int j = 1; j < n - 1; j++) {
        f[i][j] = matrix[i][j] + Math.min(
                Math.min(f[i - 1][j + 1], f[i - 1][j]),
                f[i - 1][j - 1]
        );

      }
    }

    int ans = Integer.MAX_VALUE;
    for (int v : f[n - 1]) {
      ans = Math.min(ans, v);
    }
    return ans;
  }

  public static void main(String[] args) {
    MinimumFallingPathSum m = new MinimumFallingPathSum();
    System.out.println(m.minFallingPathSum(
            new int[][]{{-19, 57}, {-40, -5}}
    ));
    System.out.println(m.minFallingPathSum(
            new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}
    ));
  }
}
