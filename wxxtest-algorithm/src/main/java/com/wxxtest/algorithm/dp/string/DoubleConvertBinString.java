package com.wxxtest.algorithm.dp.string;

public class DoubleConvertBinString {

  /**
   * 2的-n次方乘以2的n次方为1
   * <a href="https://leetcode.cn/problems/binary-number-to-string-lcci/">...</a>
   */
  public static String printBin(double num) {
    StringBuilder sb = new StringBuilder("0.");
    while (sb.length() <= 32 && num != 0) {
      num = num * 2;
      int digit = (int) num;
      sb.append(digit);
      num = num - digit;
    }
    return sb.length() > 32 ? "ERROR" : sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(printBin(0.12));
  }
}
