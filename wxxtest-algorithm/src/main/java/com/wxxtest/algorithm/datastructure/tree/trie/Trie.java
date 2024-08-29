package com.wxxtest.algorithm.datastructure.tree.trie;

import java.util.HashSet;
import java.util.Set;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，
 * 用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 */
public class Trie {
  private final Trie[] children;
  private final Set<String> words;

  public Trie() {
    children = new Trie[26];
    words = new HashSet<>();
  }

  /**
   * 1、将单词放入words中
   * 2、展开放置在trie中，
   *    2.1 以s.charAt(i)-'a'为索引
   *    2.2 递归放置在children
   */
  public void insert(String word) {
    words.add(word);
    Trie cur = this;
    //将word展开赋值
    for (char c : word.toCharArray()) {
      int cIndex = c - 'a';
      if (cur.children[cIndex] == null) {
        cur.children[cIndex] = new Trie();
      }
      cur = cur.children[cIndex];
    }
  }

  public boolean search(String word) {
    return words.contains(word);
  }

  public boolean startsWith(String prefix) {
    Trie cur = this;
    for (char c : prefix.toCharArray()) {
      int cIndex = c - 'a';
      if (cur.children[cIndex] == null) {
        return false;
      }
      cur = cur.children[cIndex];
    }
    return true;
  }

  public static void main(String[] args) {
    Trie t = new Trie();
    t.insert("apple");
    System.out.println(t.search("apple"));
    System.out.println(t.search("app"));
    System.out.println(t.startsWith("app"));
    t.insert("app");
    System.out.println(t.search("app"));
  }
}
