package com.wxxtest.algorithm.datastructure.array;

public class FindIndexOfTheFirstOccurrenceInString {

  /**
   * 遍历haystack，若发现
   * h[i] = = needle[0],则开始匹配，若不匹配
   * 则继续查找下一个，直到找到完全匹配，或者遍历haystack结束
   */
  public int strStr(String haystack, String needle) {
    int n = haystack.length();
    int m = needle.length();
    if (m > n) {
      return -1;
    }
    for (int i = 0; i + m < n; i++) {
      boolean flag = true;
      for (int j = 0; j < m; j++) {
        if (haystack.charAt(i + j) != needle.charAt(j)) {
          flag = false;
          break;
        }
      }

      if (flag) {
        return i;
      }
    }

    return -1;
  }

  public int strStrByKMP(String haystack, String needle) {
    return -1;
  }

  public static void main(String[] args) {
    FindIndexOfTheFirstOccurrenceInString f = new FindIndexOfTheFirstOccurrenceInString();
    System.out.println(f.strStr("sadbutsad", "sad"));
    System.out.println(f.strStr("leetcode", "leeto"));
  }
}
