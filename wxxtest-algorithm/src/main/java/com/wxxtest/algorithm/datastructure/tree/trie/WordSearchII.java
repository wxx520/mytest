package com.wxxtest.algorithm.datastructure.tree.trie;

import java.util.*;

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

  private Set<String> ans;

  int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

  public List<String> findWords(char[][] board, String[] words) {
    n = board.length;
    m = board[0].length;
    this.board = board;

    WSTrie trie = new WSTrie();
    for (String word : words) {
      trie.insert(word);
    }

    ans = new HashSet<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        dfs(trie, i, j);
      }
    }
    return new ArrayList<>(ans);
  }

  private void dfs(WSTrie now, int i, int j) {
    char ch = board[i][j];
    if (!now.children.containsKey(ch)) {
      return;
    }
    now = now.children.get(ch);
    if (!"".equals(now.word)) {
      ans.add(now.word);
    }
    board[i][j] = '#';
    for (int[] dir : directions) {
      int i2 = i + dir[0];
      int j2 = j + dir[1];
      if (i2 < 0 || i2 >= n || j2 < 0 || j2 >= m) {
        continue;
      }
      dfs(now, i2, j2);
    }
    board[i][j] = ch;
  }

  public static void main(String[] args) {
    WordSearchII ws = new WordSearchII();
    char[][] b1 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    System.out.println(ws.findWords(b1, new String[]{"oath", "pea", "eat", "rain"}));
    System.out.println(ws.findWords(b1, new String[]{"abcb"}));
  }
}

class WSTrie {
  String word;
  Map<Character, WSTrie> children;

  public WSTrie() {
    this.word = "";
    this.children = new HashMap<>();
  }

  public void insert(String word) {
    WSTrie cur = this;
    for (int i = 0; i < word.length(); ++i) {
      char c = word.charAt(i);
      if (!cur.children.containsKey(c)) {
        cur.children.put(c, new WSTrie());
      }
      cur = cur.children.get(c);
    }
    cur.word = word;
  }
}
