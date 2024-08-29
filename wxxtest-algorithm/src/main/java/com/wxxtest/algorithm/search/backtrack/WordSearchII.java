package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 */
public class WordSearchII {

  private char[][] board;
  private int m;
  private int n;

  int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

  /**
   * words.len = wl, words[i].len = wli,则时间复杂度为：
   * wl*n*m
   */
  public List<String> findWords(char[][] board, String[] words) {
    n = board.length;
    m = board[0].length;
    this.board = board;
    //避免重复
    Set<String> ans = new HashSet<>();
    for (String w : words) {
      boolean[][] used = new boolean[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          boolean res = dfs(i, j, 0, w, used);
          if (res) {
            ans.add(w);
          }
        }
      }
    }

    return new ArrayList<>(ans);
  }

  private boolean dfs(int i, int j, int depth, String word, boolean[][] used) {
    if (board[i][j] != word.charAt(depth) || used[i][j]) {
      return false;
    }
    if (depth == word.length() - 1) {
      return true;
    }
    //避免重复使用
    used[i][j] = true;
    for (int[] dir : directions) {
      int newI = i + dir[0];
      int newJ = j + dir[1];
      if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) {
        continue;
      }
      boolean res = dfs(newI, newJ, depth + 1, word, used);
      if (res) {
        return true;
      }
    }
    //恢复现场
    used[i][j] = false;
    return false;
  }

  public static void main(String[] args) {
    WordSearchII ws = new WordSearchII();
    char[][] b1 = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
    System.out.println(ws.findWords(b1, new String[]{"oath", "pea", "eat", "rain"}));
    System.out.println(ws.findWords(b1, new String[]{"abcb"}));
  }
}
