package com.wxxtest.algorithm.basics.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 分数背包问题
 * 给定n个物品，第i个物品的重量为wgt[i]、
 * 价值为val[i]，和一个容量为cap的背包。
 * 每个物品只能选择一次，但可以选择物品的一部分，
 * 价值根据选择的重量比例计算，问在限定背包容量下背包中物品的最大价值。
 */
public class FractionalKnapsack {

  /**
   * 尽可能选择单位重量更有价值的商品
   */
  double fractionalKnapsack(int[] wgt, int[] val, int cap) {
    int n = wgt.length;
    Item[] items = new Item[n];
    for (int i = 0; i < n; i++) {
      items[i] = new Item(wgt[i], val[i]);
    }
    Arrays.sort(items, Comparator.comparingDouble(item -> ((double) item.v / item.w)));
    double ans = 0;
    for (int i = n - 1; i >= 0; i--) {
      Item item = items[i];
      //如果容量充足则直接把当前单位价值最高的全部放进去
      if (item.w <= cap) {
        ans += item.v;
        cap -= item.w;
      } else {
        //若容量不足，剩余容量则把当前单位价值最高的放一部分进去
        ans += (double) item.v / item.w * cap;
        //已经没有剩余容量了
        break;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] wgt = new int[]{10, 20, 30, 40, 50};
    int[] val = new int[]{50, 120, 150, 210, 240};
    FractionalKnapsack fk = new FractionalKnapsack();
    System.out.println(fk.fractionalKnapsack(wgt, val, 50));
    System.out.println(fk.fractionalKnapsack(wgt, val, 52));
  }
}

class Item {
  int w; // 物品重量
  int v; // 物品价值

  public Item(int w, int v) {
    this.w = w;
    this.v = v;
  }
}