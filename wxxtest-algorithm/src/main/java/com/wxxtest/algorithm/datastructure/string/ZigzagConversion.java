package com.wxxtest.algorithm.datastructure.string;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/zigzag-conversion/description/?envType=study-plan-v2&envId=top-interview-150">...</a>
 */
public class ZigzagConversion {

  public String convert(String s, int numRows) {
    if (numRows < 2) return s;
    List<StringBuilder> rows = new ArrayList<>();
    for (int i = 0; i < numRows; i++) rows.add(new StringBuilder());
    int i = 0;
    int flag = -1;
    for (char c : s.toCharArray()) {
      rows.get(i).append(c);
      if (i == 0 || i == numRows - 1) flag = -flag;
      i += flag;
    }
    StringBuilder res = new StringBuilder();
    for (StringBuilder row : rows) res.append(row);
    return res.toString();
  }

  public static void main(String[] args) {
    ZigzagConversion zz = new ZigzagConversion();
    System.out.println(zz.convert("PAYPALISHIRING", 3));
    System.out.println(zz.convert("PAYPALISHIRING", 4));
    System.out.println(zz.convert("A", 1));
  }
}
