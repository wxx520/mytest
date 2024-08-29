package com.wxxtest.algorithm.datastructure.array;

public class CanPlaceFlowers {

  /**
   * 当前位置能种花必须同时满足
   * 1、当前位置没有种花
   * 2、前一个位置无花
   * 3、后一个位置无花
   */
  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    if (n == 0) {
      return true;
    }
    int len = flowerbed.length;
    int remain = 0;
    boolean prev = false;
    for (int i = 0; i < len; i++) {
      // 当前位置已经种花
      if (flowerbed[i] == 1) {
        prev = true;
        continue;
      }
      // 前一个位置已经种花
      if (prev) {
        prev = false;
        continue;
      }
      if (i == len - 1) {
        remain++;
        return remain >= n;
      }

      // 后一个位置已经种花
      if (flowerbed[i + 1] == 1) {
        continue;
      }

      remain++;
      prev = true;
      if (remain == n) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    CanPlaceFlowers cp = new CanPlaceFlowers();
    System.out.println(cp.canPlaceFlowers(
            new int[]{1, 0, 0, 0, 1, 0, 0},
            2
    ));
  }
}
