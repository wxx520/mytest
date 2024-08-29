package com.wxxtest.algorithm.dp.string;

import java.util.*;

/**
 * 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * <a href="https://leetcode.cn/problems/word-break/description/?envType=study-plan-v2&envId=dynamic-programming">...</a>
 */
public class WordBreak {
  private static final Map<Character, List<String>> mapDic = new HashMap<>();

  public static boolean wordBreakByBruteForce(String s, List<String> wordDict) {
    Set<Character> setDic = new HashSet<>();
    // 将字典按照首字母分组
    // 然后按组切割遍历直到最后一个单词为止

    for (String d : wordDict) {
      char firstDigit = d.charAt(0);
      List<String> list = mapDic.getOrDefault(firstDigit, new ArrayList<>());
      list.add(d);
      mapDic.put(firstDigit, list);
      for (int i = 0; i < d.length(); i++) {
        setDic.add(d.charAt(i));
      }
    }
    int len = s.length();

    for (int i = 0; i < len; i++) {
      if (!setDic.contains(s.charAt(i))) {
        return false;
      }
    }

    // 开始从头遍历切割

    return isContains(s);
  }

  private static boolean isContains(String word) {
    // 已经全部切割完成，表示存在
    if (word.length() == 0) {
      return true;
    }
    char firstDigit = word.charAt(0);
    List<String> list = mapDic.get(firstDigit);
    // 如果不存在以该字母开头的单词，肯定不能组合成功
    if (list == null) {
      return false;
    }
    // 存在，则没有以首字母开头的都去切割，直到发现全部切割完成
    for (String matchWord : list) {
      int len = matchWord.length();
      // 不是以该单词开头则该种可能性不成立
      if (len > word.length() || !matchWord.equals(word.substring(0, len))) {
        continue;
      }
      // 是以该单词开头，则继续向下分割，如果为发现分割成功则结束，否则继续下一个
      if (isContains(word.substring(len))) {
        return true;
      }
    }
    // 全部分割完也直接返回，说明分割失败
    return false;
  }

  public static boolean wordBreakByDP(String s, List<String> wordDict) {
    Set<String> wordTable = new HashSet<>(wordDict);
    int len = s.length();
    //dp[i]表示s0..si-1前i个字符是否能被字典拆分掉
    boolean[] dp = new boolean[len + 1];
    dp[0] = true;//空字符串可以
    for (int i = 1; i <= len; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && wordTable.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[len];
  }

  public static void main(String[] args) {
    System.out.println(wordBreakByDP("wxx",new ArrayList<>()));
    System.out.println(wordBreakByBruteForce("wxx",new ArrayList<>()));
  }
}
