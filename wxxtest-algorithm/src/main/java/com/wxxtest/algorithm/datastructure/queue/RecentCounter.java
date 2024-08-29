package com.wxxtest.algorithm.datastructure.queue;

import java.util.ArrayList;
import java.util.List;

public class RecentCounter {

  List<Integer> count;
  int size;

  public RecentCounter() {
    count = new ArrayList<>();
    size = 0;
  }

  public int ping(int t) {
    int ans = 1;
    int index = size - 1;
    int gap = 3000;
    while (index >= 0) {
      int temp = count.get(index);
      if (t - temp > gap) {
        break;
      }
      index--;
      ans++;
    }
    count.add(t);
    size++;
    return ans;
  }
}
