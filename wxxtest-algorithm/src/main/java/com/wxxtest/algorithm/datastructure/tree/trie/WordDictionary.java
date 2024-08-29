package com.wxxtest.algorithm.datastructure.tree.trie;

public class WordDictionary {

  private final WordDictionary[] children;
  private boolean isEnd;

  public WordDictionary() {
    children = new WordDictionary[26];
    isEnd = false;
  }

  public void addWord(String word) {
    WordDictionary cur = this;
    for (char c : word.toCharArray()) {
      int cIndex = c - 'a';
      if (cur.children[cIndex] == null) {
        cur.children[cIndex] = new WordDictionary();
      }
      cur = cur.children[cIndex];
    }
    cur.isEnd = true;
  }

  /**
   * 因为存在通配符'.'，所以所有孩子都需要遍历查找
   */
  public boolean search(String word) {
    return search(children, word, 1);
  }

  public boolean search(WordDictionary[] ds, String word, int index) {
    char c = word.charAt(index - 1);
    //如果只有一个字符，则直接遍历所有孩子是否存在该字符
    if (word.length() == index) {
      return search(ds, c);
    }

    //如果不是通配符，则首先确定该字符是否存在
    if (c != '.') {
      int cIndex = c - 'a';
      //不存在则立刻返回
      if (ds[cIndex] == null) {
        return false;
      }
      //存在则继续查找下一个字符
      return search(ds[cIndex].children, word, index + 1);
    }

    //如果是通配符，则所有孩子都需要查找，发现其中一个孩子存在既可以返回
    for (WordDictionary d : ds) {
      if (d != null && search(d.children, word, index + 1)) {
        return true;
      }
    }

    //所有孩子都不存在则返回false
    return false;
  }

  private boolean search(WordDictionary[] ds, char c) {
    if (c != '.') {
      int cIndex = c - 'a';
      return ds[cIndex] != null && ds[cIndex].isEnd;
    }

    for (WordDictionary d : ds) {
      if (d != null && d.isEnd) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {

    WordDictionary wd = new WordDictionary();
    wd.addWord("bad");
    wd.addWord("dad");
    wd.addWord("mad");
    wd.search("pad");
    System.out.println(wd.search("bad"));
    System.out.println(wd.search(".ad"));
    System.out.println(wd.search("b.."));
    System.out.println(wd.search("b.m"));
  }
}
