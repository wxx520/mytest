package com.wxxtest.algorithm.dp.bag;

public class CoinChange {

  /**
   * 定义f[i]为兑换金额为i需要的最少零钱的数量
   * 则 f[i] = sum(f[i-coins[j]])
   *
   * @param amount 需要兑换的金额
   * @param coins  零钱的种类
   * @return 能兑换成功的方式
   */
  public int change(int amount, int[] coins) {
    int ans = changeByGreedy(amount, coins);
    if (ans != -1) {
      return ans;
    }
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int coin : coins) {
      for (int i = coin; i <= amount; i++) {
        dp[i] += dp[i - coin];
      }
    }
    return dp[amount];
  }

  public int changeByGreedy(int amount, int[] coins) {
    int len = coins.length;
    int index = -1;
    for (int i = len - 1; i >= 0; i--) {
      if (amount >= coins[i]) {
        index = i;
        break;
      }
    }
    int ans = 0;
    for (int i = index; i >= 0; i--) {
      int curC = coins[i];
      if (curC > amount) {
        break;
      }
      while (amount >= curC) {
        amount -= curC;
        ans++;
      }
    }
    return amount == 0 ? ans : -1;
  }


  public static void main(String[] args) {
    CoinChange cc = new CoinChange();
    System.out.println(cc.change(5, new int[]{1, 2, 5}));
    System.out.println(cc.change(3, new int[]{2}));
    System.out.println(cc.change(10, new int[]{10}));
  }
}
