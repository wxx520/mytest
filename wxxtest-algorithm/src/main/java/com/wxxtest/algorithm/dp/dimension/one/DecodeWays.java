package com.wxxtest.algorithm.dp.dimension.one;

public class DecodeWays {

  /**
   * @param s 数字字符串
   * @return 按照[1...26]对应[A...Z]解接码的种类
   */
  public static int numDecodings(String s) {
    if (s.charAt(0) == '0') {
      return 0;
    }
    int n = s.length();
    int[] f = new int[n + 1];
    //零个字符和1个字符的接码方式都只有一种
    f[0] = 1;
    f[1] = 1;
    for (int i = 2; i <= n; i++) {
      char curChar = s.charAt(i - 1);
      char preChar = s.charAt(i - 2);
      if (curChar == '0') {
        //两个连续0不能存在或者大于等于30的不能存在
        if (preChar == '0' || (preChar - '0') > 2) {
          return 0;
        }
        //当前字符为0不能单独接码
        f[i] = f[i - 2];
      } else if (preChar == '0' || ((preChar - '0') * 10 + (curChar - '0')) > 26) {
        //当前一个字符为零，或两个字符合起来大于26时表示不能两个一起接码
        f[i] = f[i - 1];
      } else {
        f[i] = f[i - 1] + f[i - 2];
      }
    }
    return f[n];
  }

  public static int numDecodingsByNoArray(String s) {
    if (s.charAt(0) == '0') {
      return 0;
    }
    int n = s.length();
    //零个字符和1个字符的接码方式都只有一种
    int a = 1, b = 1, c = 1;//f[0], f[1], f[2]
    for (int i = 2; i <= n; i++) {
      char curChar = s.charAt(i - 1);
      char preChar = s.charAt(i - 2);
      if (curChar == '0') {
        //两个连续0不能存在或者大于等于30的不能存在
        if (preChar == '0' || (preChar - '0') > 2) {
          return 0;
        }
        //当前字符为0不能单独接码
        c = a;
      } else if (preChar == '0' || ((preChar - '0') * 10 + (curChar - '0')) > 26) {
        //当前一个字符为零，或两个字符合起来大于26时表示不能两个一起接码
        c = b;
      } else {
        c = a + b;
      }
      a = b;
      b = c;
    }
    return c;
  }

  public static void main(String[] args) {
    System.out.println(numDecodings("12"));
    System.out.println(numDecodings("226"));
    System.out.println(numDecodings("06"));
    System.out.println(numDecodings("20"));

    System.out.println(numDecodingsByNoArray("12"));
    System.out.println(numDecodingsByNoArray("226"));
    System.out.println(numDecodingsByNoArray("06"));
    System.out.println(numDecodingsByNoArray("20"));
  }
}
