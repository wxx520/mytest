package com.wxxtest.algorithm.dp.lcs;

/**
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 * <p>
 * 请你返回让 s 成为回文串的 最少操作次数 。
 * <p>
 * 「回文串」是正读和反读都相同的字符串。
 * <p>
 */
public class MinimumInsertionStepsToMakeStringPalindrome {

  /**
   * 定义f[i][j]为是s中[i,j]之间字符成为回文串所需要的最少插入数，
   * 其中0<=i<=j<n,若i>=j,则f[i][j]=0,
   * 则有如果s.charAt(i)==s.charAt(j)
   * f[i][j] = f[i+1][j-1];
   * 否则需要向左边插入或向右插入一个字符
   * f[i][j] = min(f[i+1][j]，f[i][j-1])+1;
   *
   * @param s 输入
   * @return 最少插入数
   */
  public static int minInsertions(String s) {
    int n = s.length();
    int[][] f = new int[n][n];
    for (int i = n - 1; i >= 0; i--) {
      char c = s.charAt(i);
      for (int j = i + 1; j < n; j++) {
        if (c == s.charAt(j)) {
          f[i][j] = f[i + 1][j - 1];
        } else {
          f[i][j] = Math.min(f[i][j - 1], f[i + 1][j]) + 1;
        }
      }
    }
    return f[0][n - 1];
  }

  /**
   * 找s和reverseS的最长公共子序列l，然后剩下不一样的就是要插入的
   * sLen - l
   *
   * @param s 输入
   * @return 最少操作数
   */
  public static int minInsertionsByLCS(String s) {
    int n = s.length();
    String revS = new StringBuilder(s).reverse().toString();
    if (s.equals(revS)) {
      return 0;
    }
    int[][] f = new int[n + 1][n + 1];
    for (int i = 1; i <= n; i++) {
      char c = s.charAt(i - 1);
      for (int j = 1; j <= n; j++) {
        if (c == revS.charAt(j - 1)) {
          f[i][j] = f[i - 1][j - 1] + 1;
        } else {
          f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
        }
      }
    }
    return n - f[n][n];
  }

  /**
   * 找s最长回文串l，然后剩下不一样的就是要插入的
   * sLen - l
   *
   * @param s 输入
   * @return 最少操作数
   */
  public static int minInsertionsByPalindrome(String s) {
    int n = s.length();
    //f[i][j]为s种[i,j]之间字符组成的最长回文串
    int[][] f = new int[n][n];
    for (int i = n - 1; i >= 0; i--) {
      //则有i=j,f[i][j]=1;
      f[i][i] = 1;
      char c = s.charAt(i);
      for (int j = i + 1; j < n; j++) {
        if (s.charAt(j) == c) {
          f[i][j] = f[i + 1][j - 1] + 2;
        } else {
          f[i][j] = Math.max(f[i][j - 1], f[i + 1][j]);
        }
      }
    }
    return n - f[0][n - 1];
  }

  public static void main(String[] args) {
    System.out.println(minInsertions("zaazz"));
    System.out.println(minInsertionsByPalindrome("zzazz"));
    System.out.println(minInsertionsByLCS("zzsff"));
  }
}
