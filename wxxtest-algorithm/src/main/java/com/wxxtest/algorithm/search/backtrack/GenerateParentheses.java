package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
  ArrayList<String>[] cache = new ArrayList[100];

  public List<String> generateParenthesis(int n) {
    return generate(n);
  }

  private List<String> generate(int n) {
    if (cache[n] != null) {
      return cache[n];
    }
    ArrayList<String> ans = new ArrayList<>();
    if (n == 0) {
      ans.add("");
    } else {
      for (int i = 0; i < n; i++) {
        for (String left : generate(i)) {
          for (String right : generate(n - i - 1)) {
            ans.add("(" + left + ")" + right);
          }
        }
      }
    }
    cache[n] = ans;
    return ans;
  }

  private List<String> ans;
  private int max;

  public List<String> generateParenthesis1(int n) {
    ans = new ArrayList<>();
    max = n;
    backtrack(new StringBuffer(), 0, 0);
    return ans;
  }

  private void backtrack(StringBuffer cur, int open, int close) {
    if (cur.length() == max * 2) {
      ans.add(cur.toString());
      return;
    }
    if (open < max) {
      cur.append('(');
      backtrack(cur, open + 1, close);
      cur.deleteCharAt(cur.length() - 1);
    }
    if (close < open) {
      cur.append(')');
      backtrack(cur, open, close + 1);
      cur.deleteCharAt(cur.length() - 1);
    }
  }

  public static void main(String[] args) {
    GenerateParentheses gp = new GenerateParentheses();
    System.out.println(gp.generateParenthesis(1));
    System.out.println(gp.generateParenthesis1(1));
    System.out.println(gp.generateParenthesis(3));
    System.out.println(gp.generateParenthesis1(3));
  }
}
