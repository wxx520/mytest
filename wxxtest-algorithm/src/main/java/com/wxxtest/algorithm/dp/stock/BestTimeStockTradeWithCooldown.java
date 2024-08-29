package com.wxxtest.algorithm.dp.stock;

/**
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class BestTimeStockTradeWithCooldown {

  /**
   * 定义buy[i],为第i天结束之后仍持有股票的累积最大收益
   * 定义sell[i],为第i天结束之后不持有股票但不处于冷静期的最大收益
   * 定义cool[i],为第i天结束之后为冷冻期的最大收益
   * 则最终最大收益为max(sell[i],cool[i])
   * 则有buy[0]=-prices[0],sell[0]=0,cool[0]=0,且
   * buy[i] = max(buy[i-1], sell[i-1]-prices[i]),
   * sell[i] = max(sell[i-1], cool[i-1]);
   * cool[i] = buy[i-1]+prices[i];
   *
   * @param prices 每天的股票价格
   * @return 若干次股票交易后能获取的最大收益
   */
  public static int maxProfit(int[] prices) {
    int b = -prices[0];
    int s = 0, c = 0;
    for (int i = 1; i < prices.length; i++) {
      int curPrice = prices[i];
      int b1 = Math.max(b, s - curPrice);
      int s1 = Math.max(s, c);
      int c1 = b + curPrice;
      b = b1;
      c = c1;
      s = s1;
    }
    return Math.max(s, c);
  }

  public static void main(String[] args) {
    System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2}));
  }
}
