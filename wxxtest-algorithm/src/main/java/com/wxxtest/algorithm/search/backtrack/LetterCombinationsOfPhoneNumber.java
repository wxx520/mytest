package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {

  private String[] dic;
  private String originS;

  private List<String> ans;

  public List<String> letterCombinations(String digits) {
    if (digits == null || digits.length() < 1) {
      return new ArrayList<>();
    }
    init(digits);
    backtrack(new StringBuilder(), 0, digits.length());
    return ans;
  }

  private void backtrack(StringBuilder cur, int index, int max) {
    if (cur.length() == max) {
      ans.add(cur.toString());
      return;
    }
    for (char c : dic[originS.charAt(index) - '2'].toCharArray()) {
      cur.append(c);
      backtrack(cur, index + 1, max);
      cur.deleteCharAt(cur.length() - 1);
    }
  }

  private void init(String digits) {
    originS = digits;
    ans = new ArrayList<>();
    dic = new String[8];
    dic[0] = "abc";
    dic[1] = "def";
    dic[2] = "ghi";
    dic[3] = "jkl";
    dic[4] = "mno";
    dic[5] = "pqrs";
    dic[6] = "tuv";
    dic[7] = "wxyz";
  }

  public static void main(String[] args) {
    LetterCombinationsOfPhoneNumber l = new LetterCombinationsOfPhoneNumber();
    System.out.println(l.letterCombinations("23"));
    System.out.println(l.letterCombinations(""));
    System.out.println(l.letterCombinations("2"));
  }
}
