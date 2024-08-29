package com.wxxtest.algorithm.search.backtrack;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 */
public class WordSearch {

  private char[][] board;
  private String word;

  private int len;
  private int m;
  private int n;

  int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

  /**
   * 以网格中的每一个字符为起点，从上下左右四个方向进行遍历
   * 其中若board[i][j] = word.charAt(depth),则继续遍历
   * 直至发现depth==word.len
   * 否则返回false
   *
   * @param board 原始二维字符网格
   * @param word  需要匹配的单词串
   * @return 是否能匹配成功
   */
  public boolean exist(char[][] board, String word) {
    n = board.length;
    m = board[0].length;
    len = word.length();
    if (m * n < len) {
      return false;
    }
    this.board = board;
    this.word = word;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        boolean res = dfs(i, j, 0);
        if (res) {
          return true;
        }
      }

    }
    return false;
  }

  private boolean dfs(int i, int j, int depth) {
    if (board[i][j] != word.charAt(depth)) {
      return false;
    }
    if (depth == len - 1) {
      return true;
    }
    //避免重复使用，用过的将值设置为空字符
    char cTemp = board[i][j];
    board[i][j] = ' ';
    for (int[] dir : directions) {
      int newI = i + dir[0];
      int newJ = j + dir[1];
      if (newI < 0 || newI > n - 1 || newJ < 0 || newJ > m - 1) {
        continue;
      }
      boolean res = dfs(newI, newJ, depth + 1);
      if (res) {
        return true;
      }
    }
    //恢复为原来的字符串
    board[i][j] = cTemp;
    return false;
  }

  public static void main(String[] args) {
    WordSearch ws = new WordSearch();
    char[][] b1 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    System.out.println(ws.exist(b1, "ABCCED"));
    System.out.println(ws.exist(b1, "SEE"));
    System.out.println(ws.exist(b1, "ABCB"));
  }
}
