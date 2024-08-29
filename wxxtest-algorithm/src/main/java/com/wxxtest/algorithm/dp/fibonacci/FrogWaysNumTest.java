package com.wxxtest.algorithm.dp.fibonacci;

public class FrogWaysNumTest {

  /**
   * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
   * <p>
   * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
   * <p>
   * 链接：<a href="https://leetcode.cn/leetbook/read/illustration-of-algorithm/57hyl5/">...</a>
   * 来源：力扣（LeetCode）
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   * <p>
   * 青蛙每次仅有两种跳跃方式，假设有n级台阶，其跳法记为f(n),则
   * f(n-1)+最后一级只跳一下
   * f(n-2)+最后两级做一次跳跃，两种跳跃方式的和就是f(n)
   * 即 f(n)=f(n-1)+f(n-2);
   * 斐波那契函数
   *
   */
  public static int numWaysByRecursion(int n) {
    if (n <= 1) {
      return 1;
    }
    return numWaysByRecursion(n - 1) + numWaysByRecursion(n - 2);
  }

  public static int numWaysByDP(int n) {
    if (n <= 1) {
      return 1;
    }
    int[] numWays = new int[n + 1];
    numWays[0] = 1;
    numWays[1] = 1;
    for (int i = 2; i <= n; i++) {
      numWays[i] = (numWays[i - 1] + numWays[i - 2]) % 1000000007;
    }
    return numWays[n];
  }

  public static int numWaysByDP1(int n) {
    if (n <= 1) {
      return 1;
    }
    int prev1 = 1;
    int pre2 = 1;
    int numWays = prev1;
    for (int i = 2; i <= n; i++) {
      numWays = (prev1 + pre2) % 1000000007;
      pre2 = prev1;
      prev1 = numWays;
    }
    return numWays;
  }

  public static void main(String[] args) {
    long ct = System.currentTimeMillis();
    System.out.println(numWaysByDP(187));
    System.out.println(System.currentTimeMillis() - ct);
    long ct1 = System.currentTimeMillis();
    System.out.println(numWaysByDP1(187));
    System.out.println(System.currentTimeMillis() - ct1);
    System.out.println(numWaysByRecursion(187));
  }
}
