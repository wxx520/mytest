package com.wxxtest.algorithm.datastructure.tree.bt;

public class LetterTilePossibilities {

  public int numTilePossibilities(String tiles) {
    int len = tiles.length();
    if (len <= 1) {
      return len;
    }
    int[] count = new int[26];
    for (char c : tiles.toCharArray()) {
      count[c - 'A']++;
    }
    return dfs(count);
  }

  private int dfs(int[] count) {
    int res = 0;
    for (int i = 0; i < 26; i++) {
      if (count[i] == 0) {
        continue;
      }
      res++;
      count[i]--;
      res += dfs(count);
      count[i]++;
    }
    return res;
  }

  public static void main(String[] args) {
    LetterTilePossibilities l = new LetterTilePossibilities();
    System.out.println(l.numTilePossibilities("ABB"));
    System.out.println(l.numTilePossibilities("AAABBC"));
    System.out.println(l.numTilePossibilities("V"));
  }
}
