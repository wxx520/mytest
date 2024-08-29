package com.wxxtest.algorithm.dp.matrix;

public class UniquePathsII {

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length, n = obstacleGrid[0].length;
    //如果目的地本身是障碍物直接返回不存在路径
    if (obstacleGrid[m - 1][n - 1] == 1) {
      return 0;
    }
    //f[i][j]为从起点到达[i,j]的路径种类
    int[][] f = new int[m][n];
    f[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
    for (int i = 1; i < n; i++) {
      if (obstacleGrid[0][i] == 1 || f[0][i - 1] == 0) {
        f[0][i] = 0;
      } else {
        f[0][i] = 1;
      }
    }

    for (int i = 1; i < m; i++) {
      if (obstacleGrid[i][0] == 1 || f[i - 1][0] == 0) {
        f[i][0] = 0;
      } else {
        f[i][0] = 1;
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          f[i][j] = 0;
        } else {
          f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
      }
    }

    return f[m - 1][n - 1];
  }

  public static void main(String[] args) {
    UniquePathsII test = new UniquePathsII();
    System.out.println(test.uniquePathsWithObstacles(
            new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    System.out.println(test.uniquePathsWithObstacles(
            new int[][]{{0, 1}, {0, 0}}
    ));
  }
}
