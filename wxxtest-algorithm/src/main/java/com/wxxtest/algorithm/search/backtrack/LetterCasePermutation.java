package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {

  private List<String> ans;
  private char[] path;

  /**
   * 假设s上有i个字母，则有2的i次方中可能性
   *
   * @param s 原始字符串
   * @return 字母变换大小写后的所有组合
   */
  public List<String> letterCasePermutation(String s) {
    path = s.toCharArray();
    ans = new ArrayList<>();
    dfs(0);
    return ans;
  }

  private void dfs(int cur) {
    if (cur == path.length) {
      ans.add(new String(path));
      return;
    }
    char c = path[cur];
    int temp = c - 'A';

    //大写变小写
    if (temp >= 0 && temp <= 25) {
      path[cur] = (char) (c + 32);
      dfs(cur + 1);
      path[cur] = c;
    } else if (temp >= 32 && temp <= 57) {
      //小写变大写
      path[cur] = (char) (c - 32);
      dfs(cur + 1);
      path[cur] = c;
    }
    //不变
    dfs(cur + 1);
  }

  public static void main(String[] args) {
    LetterCasePermutation l = new LetterCasePermutation();
    System.out.println(l.letterCasePermutation("a1b2"));
  }

}
