package com.wxxtest.algorithm.dp.stock;

public class BestTimeStockTradeOnce {

  /**
   * 组开始递减的时候会找最小值，只有开始递增的时候计算差值
   *
   * @param prices 每天的股票价格
   * @return 一次交易能获取的最大收益
   */
  public static int maxProfit(int[] prices) {
    int len = prices.length;
    if (len <= 1) {
      return 0;
    }
    int min = prices[0];
    int max = prices[0];
    int ret = 0;
    for (int i = 1; i < len; i++) {
      if (prices[i] > max) {
        ret = Math.max(ret, prices[i] - min);
        max = prices[i];
      } else if (prices[i] < min) {
        min = prices[i];
        max = min;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
  }
}
