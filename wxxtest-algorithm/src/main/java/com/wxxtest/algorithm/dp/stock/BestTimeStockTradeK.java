package com.wxxtest.algorithm.dp.stock;

public class BestTimeStockTradeK {

  public static int maxProfit(int k, int[] prices) {
    int len = prices.length;
    if (len <= 1) {
      return 0;
    } else if (len == 2) {
      return prices[0] >= prices[1] ? 0 : prices[1] - prices[0];
    }
    int states = k + k;
    int[] trade = new int[states];
    for (int i = 0; i < states; i = i + 2) {
      trade[i] = -prices[0];
    }
    for (int i = 1; i < len; i++) {
      int curPrice = prices[i];
      trade[0] = Math.max(trade[0], -curPrice);
      for (int j = 1; j < states; j++) {
        if (j % 2 == 1) {//表示卖出
          trade[j] = Math.max(trade[j], trade[j - 1] + curPrice);
        } else {//买进
          trade[j] = Math.max(trade[j], trade[j - 1] - curPrice);
        }

      }
    }
    return trade[states - 1];
  }

  public static void main(String[] args) {
    System.out.println(maxProfit(2, new int[]{2, 4, 1}));
    System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
  }
}
