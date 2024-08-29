package com.wxxtest.algorithm.dp.stock;

public class BestTimeStockTradeWithTransactionFee {

  /**
   * b[i]为第i天结束后仍持有股票的最大收益
   * s[i]为第i天结束后手上不持有股票的最大收益
   * 则有b[0] = -prices[0]-fee,s[0] = 0;且
   * b[i] = max(b[i-1],s[i-1]-prices[i]-fee);
   * s[i] = max(s[i-1], b[i-1]+prices[i]);
   *
   * @param prices 每天的股票价格
   * @param fee    每次的手续费
   * @return 进行若干次交易后的最大收益
   */
  public static int maxProfit(int[] prices, int fee) {
    int b = -prices[0] - fee;
    int s = 0;
    for (int i = 1; i < prices.length; i++) {
      int b1 = Math.max(b, s - prices[i] - fee);
      int s1 = Math.max(s, b + prices[i]);
      b = b1;
      s = s1;
    }
    return s;
  }

  /**
   * @param prices 每天股票的价格
   * @param fee    每次交易的手续费
   * @return 若干次交易后的最大收益
   */
  public static int maxProfitByGreedy(int[] prices, int fee) {
    int buy = prices[0] + fee;
    int profit = 0;
    for (int i = 1; i < prices.length; i++) {
      int curPrice = prices[i];
      if (curPrice + fee < buy) {
        buy = curPrice + fee;
      } else if (curPrice > buy) {
        profit += (curPrice - buy);
        buy = curPrice;
      }
    }
    return profit;
  }

  public static void main(String[] args) {
    System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    System.out.println(maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));

    System.out.println(maxProfitByGreedy(new int[]{1, 3, 2, 8, 4, 9}, 2));
    System.out.println(maxProfitByGreedy(new int[]{1, 3, 7, 5, 10, 3}, 3));
  }
}
