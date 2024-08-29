package com.wxxtest.algorithm.dp.matrix;

public class MaximalSquare {
  /**
   * 在一个由 '0' 和 '1' 组成的二维矩阵内，
   * 找到只包含 '1' 的最大正方形，并返回其面积。
   * "滑动面积"，定义
   * f[i][j]为以[i,j]为右下角的最大正方形边长
   * 则有，令m=matrix.len, n = matrix[0].len
   * 若matrix[i][j]=='0', 则f[i][j]=0
   * 否则
   * f[0][j] = matrix[0][j]=='1'?1:0，
   * f[i][0] = matrix[i][0]=='1'?1:0，
   * 其中0<=i<m，0<=j<n;
   * f[i][j] = 1+ min(f[i][j-1],f[i-1][j-1],f[i-1][j])
   * 0<i<m,0<j<n；
   * 则最大面积为max(f[i][j])的平方
   */
  public int maximalSquare(char[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int[][] f = new int[m][n];
    int maxSide = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] != '0') {
          if (i == 0 || j == 0) {
            f[i][j] = 1;
          } else {
            f[i][j] = 1 + Math.min(
                    Math.min(f[i][j - 1], f[i - 1][j]),
                    f[i - 1][j - 1]
            );
          }
          maxSide = Math.max(maxSide, f[i][j]);
        }
      }
    }
    return maxSide * maxSide;
  }

  public static void main(String[] args) {
    MaximalSquare m = new MaximalSquare();
    System.out.println(m.maximalSquare(
            new char[][]{
                    {'1', '0', '1', '0', '0'},
                    {'1', '0', '1', '1', '1'},
                    {'1', '1', '1', '1', '1'},
                    {'1', '0', '0', '1', '0'}}
    ));
    System.out.println(m.maximalSquare(
            new char[][]{
                    {'0', '1'},
                    {'1', '0'}}
    ));
    System.out.println(m.maximalSquare(
            new char[][]{
                    {'0'}}
    ));
  }

}
