package com.wxxtest.algorithm.dp.dimension.one;

public class CountWaysToBuildGoodStrings {

  /**
   * 走楼梯的变体
   * 定义f[i]表示字符长度为i按照zero或one组成字符串的种类，则
   * f[0] = 1,
   * f[i] = f[i-one]+f[i-zero];
   */
  public static int countGoodStrings(int low, int high, int zero, int one) {
    int[] f = new int[high + 1];
    int minStep;
    //从长的那个下一个开始走
    if (zero == one) {
      f[zero] = 2;
      minStep = zero;
    } else if (zero > one) {
      //每次至少走one步，因此走one个台阶只有一种走法
      for (int i = one; i < zero; i = i + one) {
        f[i] = 1;
      }
      //如果能整出，则走zero步有两种走法，否则只能一次走zero步
      f[zero] = (zero % one == 0) ? 2 : 1;
      minStep = zero;
    } else {
      for (int i = zero; i < one; i = i + zero) {
        f[i] = 1;
      }
      f[one] = (one % zero == 0) ? 2 : 1;
      minStep = one;
    }
    for (int i = minStep + 1; i <= high; i++) {
      f[i] = (f[i - zero] + f[i - one]) % (1000 * 1000 * 1000 + 7);
    }
    int ans = 0;
    for (int i = low; i <= high; i++) {
      ans = (ans + f[i]) % (1000 * 1000 * 1000 + 7);
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(countGoodStrings(3, 3, 1, 1));
    System.out.println(countGoodStrings(2, 3, 1, 2));
    System.out.println(countGoodStrings(200, 200, 10, 1));

  }
}
