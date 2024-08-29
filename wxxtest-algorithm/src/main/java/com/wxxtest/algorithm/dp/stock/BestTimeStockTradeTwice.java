package com.wxxtest.algorithm.dp.stock;

public class BestTimeStockTradeTwice {

  /**
   * buy1[i] 第i天结束后买了一次股票 的最大收益
   * sell1[i] 第i天结束后完成一次交易 的最大收益
   * buy2[i] 第i天结束后买了第二次股票 的最大收益
   * sell1[i] 第i天结束后完成两次交易 的最大收益
   * 则有buy1[0] = -prices[0]
   * sell1[0] = 0
   * buy2[0] = -prices[0]
   * sell2[0] = 0;
   * 且有：
   * buy1[i] = max(buy1[i-1], -prices[i]);
   * sell1[i] = max(sell1[i-1], buy1[i-1]+prices[i]);
   * buy2[i] = max(buy2[i-1], sell1[i-1]-prices[i]);
   * sell2[i] = max(sell2[i-1], buy2[i-1]+prices[i]);
   *
   * @param prices 每天的股票价格
   * @return 最多交易两次可以获取的最大收益
   */
  public static int maxProfit(int[] prices) {
    int b1 = -prices[0];
    int s1 = 0;
    int b2 = -prices[0];
    int s2 = 0;
    for (int i = 1; i < prices.length; i++) {
      int curPrice = prices[i];
      b1 = Math.max(b1, -curPrice);
      s1 = Math.max(s1, b1 + curPrice);
      b2 = Math.max(b2, s1 - curPrice);
      s2 = Math.max(s2, b2 + curPrice);
    }
    return s2;
  }

  public static void main(String[] args) {
    System.out.println(maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));

    System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}));

    System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
  }
}
