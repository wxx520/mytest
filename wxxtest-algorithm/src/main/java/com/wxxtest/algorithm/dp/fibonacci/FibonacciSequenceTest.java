package com.wxxtest.algorithm.dp.fibonacci;

import java.math.BigInteger;

/**
 * 斐波那契数列
 */
public class FibonacciSequenceTest {

  /**
   * 递归算法时间复杂度O(n),空间复杂度O(n)
   */
  public static BigInteger fib(int n) {
    if (n <= 2) {
      return BigInteger.valueOf(1);
    }
    BigInteger[] resultArr = new BigInteger[n];
    resultArr[0] = BigInteger.valueOf(1);
    resultArr[1] = BigInteger.valueOf(1);
    for (int i = 2; i < resultArr.length; i++) {
      resultArr[i] = resultArr[i - 1].add(resultArr[i - 2]);
    }
    return resultArr[n - 1];
  }

  /**
   * 时间复杂度O(n),空间复杂度O(1)
   */

  public static BigInteger fib1(int n) {
    if (n <= 2) {
      return BigInteger.valueOf(1);
    }
    BigInteger current = BigInteger.valueOf(1);
    BigInteger prev1 = BigInteger.valueOf(1);
    BigInteger prev2 = BigInteger.valueOf(1);
    for (int i = 2; i < n; i++) {
      current = prev1.add(prev2);
      prev2 = prev1;
      prev1 = current;
    }
    return current;
  }

  /**
   * 递归算法时间复杂度O(2^n),空间复杂度O(n)
   */
  public static BigInteger fibByRecursion(int n) {
    if (n <= 2) {
      return BigInteger.valueOf(1);
    }
    return fibByRecursion(n - 1).add(fibByRecursion(n - 2));
  }

  static int fibonacci(int n, int[] dp) {
    if (n == 0) return 0;           // 返回 f(0)
    if (n == 1) return 1;           // 返回 f(1)
    if (dp[n] != 0) return dp[n];   // 若 f(n) 以前已经计算过，则直接返回记录的解
    dp[n] = fibonacci(n - 1, dp) + fibonacci(n - 2, dp); // 将 f(n) 则记录至 dp
    return dp[n];
  }

  /**
   * 记忆化递归递归算法时间复杂度O(n),空间复杂度O(n)
   */
  public static int fibByMemorizedRecursion(int n) {
    int[] dp = new int[n + 1];
    return fibonacci(n, dp);
  }

  public static void main(String[] args) {
    for (int i = 1; i < 30; i++) {
      System.out.print(fib(i) + " ****** ");
      System.out.println(fibByMemorizedRecursion(i));
      System.out.println(fib(i));
      System.out.println(fib1(i));
      System.out.println(fibByRecursion(i));
    }

  }
}
