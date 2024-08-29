package com.wxxtest.algorithm.dp.dimension.one;

import java.util.Arrays;

/**
 * 给定不同的面额，用最少得数量兑换零钱
 */
public class CoinChange {

  /**
   * 定义f[i]兑换i元需要的最少零钱数量，
   * f[i] = min(f(i-coins[j])+1)
   *
   * @param coins  零钱的面额
   * @param amount 需要兑换的金额
   * @return 兑换最小零钱数量
   */
  public static int coinChange(int[] coins, int amount) {
    if (amount == 0) {
      return 0;
    }
    int max = amount + 1;
    int[] f = new int[max];
    Arrays.fill(f, max);
    Arrays.sort(coins);
    f[0] = 0;
    for (int i = 1; i < max; i++) {
      for (int coin : coins) {
        if (i < coin) {
          break;
        }
        f[i] = Math.min(f[i], f[i - coin] + 1);
      }
    }
    return f[amount] > amount ? -1 : f[amount];
  }


  public int coinChangeByDFS(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }
    return dfs(coins, amount, new int[amount]);
  }

  private int dfs(int[] coins, int remain, int[] count) {
    if (remain < 0) {
      return -1;
    }

    if (remain == 0) {
      return 0;
    }

    if (count[remain - 1] != 0) {
      return count[remain - 1];
    }

    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int res = dfs(coins, remain - coin, count);
      if (res >= 0 && res < min) {
        min = res + 1;
      }
    }
    count[remain - 1] = min == Integer.MAX_VALUE ? -1 : min;
    return count[remain - 1];
  }

  public static void main(String[] args) {
    CoinChange cc = new CoinChange();
    System.out.println(cc.coinChangeByDFS(new int[]{1, 2, 5}, 11));
    System.out.println(cc.coinChangeByDFS(new int[]{2}, 3));
    System.out.println(cc.coinChangeByDFS(new int[]{1}, 0));

    System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    System.out.println(coinChange(new int[]{2}, 3));
    System.out.println(coinChange(new int[]{1}, 0));
  }
}
