package com.wxxtest.algorithm.datastructure.string;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class LongestCommonPrefix {

  /**
   * 最长公共前缀两个关键因素
   * 数组的长度n，以及前缀的长度
   * 时间复杂度为n*min(str[i].len)
   */
  public String longestCommonPrefix(String[] strs) {
    int len = strs.length;
    String firstStr = strs[0];
    //如果长度为1直接返回第一个字符串
    if (len == 1) {
      return firstStr;
    }
    //符合条件的前缀长度
    int index = 0;
    boolean flag = true;
    while (flag) {
      //最长前缀不会大于人一个字符串的长度
      if (index == firstStr.length()) {
        break;
      }
      //需要比较的字符
      char ch = firstStr.charAt(index);

      //从第一个字符开始逐个比较，若发现不一样或超出字符串长度则终止比较
      for (int i = 1; i < len; i++) {
        if (index == strs[i].length() || strs[i].charAt(index) != ch) {
          flag = false;
          break;
        }
      }
      if (flag) {
        index++;
      }
    }
    return strs[0].substring(0, index);
  }

  public static void main(String[] args) {
    LongestCommonPrefix lc = new LongestCommonPrefix();
    System.out.println(lc.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    System.out.println(lc.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
  }
}
