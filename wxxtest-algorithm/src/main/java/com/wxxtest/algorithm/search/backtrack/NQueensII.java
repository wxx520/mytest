package com.wxxtest.algorithm.search.backtrack;

public class NQueensII {

  private int ans;
  private int n;

  private int[][] used;

  public int totalNQueens(int n) {
    if (n == 1) {
      return 1;
    }
    this.n = n;
    ans = 0;
    used = new int[n][n];
    dfs(0);
    return ans;
  }

  private void dfs(int depth) {
    if (depth == n) {
      ans++;
      return;
    }
    for (int i = 0; i < n; i++) {
      if (used[depth][i] > 0) {
        continue;
      }
      fillUsedMap(depth, i, 1);
      dfs(depth + 1);
      fillUsedMap(depth, i, -1);
    }
  }

  /**
   * 找出与used[queenX][queenY]存在以下关系use[i][j]:
   * 同一行    ：queenX = i
   * 或同一列  ：queenY = j
   * 或对角线上：queenX+queenY=i+j || queenX-queenY=i-j
   * 使得 use[i][j] = use[i][j]+v;
   * 方便让后续的皇后的选择避开这些位置
   *
   * @param queenX 行的位置
   * @param queenY 列的位置
   * @param v      将行或列相同或对角线上的值加上v
   */
  private void fillUsedMap(int queenX, int queenY, int v) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == queenX || queenY == j || i + j == queenX + queenY || i - j == queenX - queenY) {
          used[i][j] += v;
        }
      }
    }
  }

  public int totalNQueens1(int n) {
    return solve(n, 0, 0, 0, 0);
  }

  /**
   *
   * @param n 皇后的个数
   * @param row 当前行数(已选择的皇后个数)
   * @param columns N位二进制数，1表示已经被选择的列，0表示未被选择的列
   * @param diagonals1 N位二进制数，1表示右对角线已经被选择，0表示未被西安则
   * @param diagonals2 N位二进制数，1表示左对角线已经被选择，0表示未被西安则
   * @return n皇后解决方案的数量
   */
  public int solve(int n, int row, int columns, int diagonals1, int diagonals2) {
    if (row == n) {
      return 1;
    } else {
      int count = 0;
      int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
      while (availablePositions != 0) {
        int position = availablePositions & (-availablePositions);
        availablePositions = availablePositions & (availablePositions - 1);
        count += solve(n, row + 1, columns | position, (diagonals1 | position) << 1,
                (diagonals2 | position) >> 1);
      }
      return count;
    }
  }

  public static void main(String[] args) {
    NQueensII nq2 = new NQueensII();
    System.out.println(nq2.totalNQueens(4));
    System.out.println(nq2.totalNQueens(8));
    System.out.println(nq2.totalNQueens1(8));

    System.out.println(nq2.totalNQueens(1));
  }

}
