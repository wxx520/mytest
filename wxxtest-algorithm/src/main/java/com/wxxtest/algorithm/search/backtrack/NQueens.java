package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
public class NQueens {

  private List<List<String>> ans;
  private List<Integer> path;

  private int[][] used;

  private int n;

  public List<List<String>> solveNQueens(int n) {
    ans = new ArrayList<>();
    if (n == 1) {
      List<String> list = new ArrayList<>();
      list.add("Q");
      ans.add(list);
      return ans;
    }
    path = new ArrayList<>();
    used = new int[n][n];
    this.n = n;
    dfs(0);
    return ans;
  }

  /**
   * @param depth 当前已经遍历的行数
   */
  private void dfs(int depth) {
    if (depth == n) {
      ans.add(generateBoard(path));
      return;
    }
    for (int i = 0; i < n; i++) {
      //表示该列已经被使用过了
      if (used[depth][i] > 0) {
        continue;
      }
      path.add(i);
      fillUsedMap(depth, i, 1);
      dfs(depth + 1);
      fillUsedMap(depth, i, -1);
      path.remove(path.size() - 1);
    }

  }

  /**
   * 找出与used[queenX][queenY]存在以下关系use[i][j]:
   * 同一行    ：queenX = i
   * 或同一列  ：queenY = j
   * 或对角线上：queenX+queenY=i+j || queenX-queenY=i-j
   * 使得 use[i][j] = use[i][j]+v;
   * 方便让后续的皇后的选择避开这些位置
   * @param queenX 行的位置
   * @param queenY 列的位置
   * @param v 将行或列相同或对角线上的值加上v
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

  private List<String> generateBoard(List<Integer> path) {
    List<String> board = new ArrayList<>();
    char[] row = new char[n];
    Arrays.fill(row, '.');
    for (int num : path) {
      row[num] = 'Q';
      board.add(new String(row));
      row[num] = '.';
    }
    return board;
  }


  public static void main(String[] args) {
    NQueens nq = new NQueens();
    System.out.println(nq.solveNQueens(4));
    System.out.println(nq.solveNQueens(1));
  }

}
