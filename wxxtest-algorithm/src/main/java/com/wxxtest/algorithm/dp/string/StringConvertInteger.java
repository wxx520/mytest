package com.wxxtest.algorithm.dp.string;

import java.util.HashMap;
import java.util.Map;

public class StringConvertInteger {

  /**
   * 题目描述
   * <a href="https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/description/">...</a>
   * str = 首部空格+符号位+数字+非数字字符
   * 1、首部空格删除之
   * 2、符号位保存
   * 3、数字拼接：若拼接结果为res，当前数字位为x，则res=10*res+x
   * 4、非字符部分结束，直接返回
   *
   * @param str 原始字符串
   * @return 转换为int后的结果
   */
  public static int strToInt(String str) {
    int res = 0, boundary = Integer.MAX_VALUE / 10;
    int i = 0, len = str.length(), sign = 1;
    if (len == 0) return 0;
    while (str.charAt(i) == ' ') {
      //略过首部空格的同时，检查字符串是否只有空格
      if (++i == len) {
        return 0;
      }
    }
    //检查并保存符号位
    if (str.charAt(i) == '-') {
      sign = -1;
    }
    char curChar = str.charAt(i);
    //表示存在符号位，继续向下检测数字部分
    if (curChar == '-' || curChar == '+') {
      i++;
    }
    for (int j = i; j < len; j++) {
      curChar = str.charAt(j);
      //非数字部分，结束
      if (curChar > '9' || curChar < '0') {
        break;
      }
      //说明要越界了
      if (res > boundary || res == boundary && curChar > '7') {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      res = res * 10 + (curChar - '0');
    }
    return sign * res;
  }

  public static int strToIntByAutomaton(String str) {
    Automaton auto = new Automaton();
    for (int i = 0; i < str.length(); i++) {
      auto.fill(str.charAt(i));
      if (auto.isEndState()) {
        break;
      }
    }
    return auto.getAns();
  }

  public static void main(String[] args) {

    System.out.println(strToInt("+123ad"));
    System.out.println(strToIntByAutomaton("+123ad"));
  }

}

class Automaton {
  private int sign = 1;

  /**
   * 大于该值，乘以10后就会越界
   */
  private static final int boundary1 = Integer.MAX_VALUE / 10;

  /**
   * 大于boundary1+boundary2，则会越界
   */
  private static final int boundary2 = Integer.MAX_VALUE % 10;

  private int ans = 0;
  private String state = "start";

  private final Map<String, String[]> table = new HashMap<>() {{
    put("start", new String[]{"start", "signed", "in_number", "end"});
    put("signed", new String[]{"end", "end", "in_number", "end"});
    put("in_number", new String[]{"end", "end", "in_number", "end"});
    put("end", new String[]{"end", "end", "end", "end"});
  }};

  /**
   * 根据输入更新状态机状态，到终态后，任何输入都不会改变状态
   *
   * @param c 输入的状态
   */
  public void fill(char c) {
    if (isEndState()) {
      return;
    }
    state = table.get(state)[convertStateUpdateIndex(c)];
    if ("in_number".equals(state)) {
      //超出最大值或最小值
      if (ans > boundary1 || ans == boundary1 && (c - '0') > boundary2) {
        ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        state = "end";
      } else {
        ans = ans * 10 + (c - '0');
      }
    } else if ("signed".equals(state) && c == '-') {
      sign = -1;
    }
  }

  private int convertStateUpdateIndex(char c) {
    if (c == ' ') {
      return 0;
    }

    if (c == '+' || c == '-') {
      return 1;
    }

    if (c <= '9' && c >= '0') {
      return 2;
    }

    return 3;
  }

  public boolean isEndState() {
    return state.equals("end");
  }

  public int getAns() {
    if (ans == Integer.MAX_VALUE && sign == 1) {
      return Integer.MAX_VALUE;
    } else if (ans == Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }
    return sign * ans;
  }
}
