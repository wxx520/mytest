package com.wxxtest.algorithm.datastructure.queue.pq;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SuperUglyNumber {
  public int nthSuperUglyNumber(int n, int[] primes) {
    int m = primes.length;
    PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    for (int i = 0; i < m; i++) {
      q.add(new int[] { primes[i], i, 0 });
    }
    int[] ans = new int[n];
    ans[0] = 1;
    for (int j = 1; j < n;) {
      int[] poll = q.poll();
      int val = poll[0], i = poll[1], idx = poll[2];
      if (val != ans[j - 1])
        ans[j++] = val;
      q.add(new int[] { ans[idx + 1] * primes[i], i, idx + 1 });
    }
    return ans[n - 1];
  }

  /**
   *https://www.suanfa8.com/priority-queue/solutions/0313-super-ugly-number
   * TODO
   */
  public int nthSuperUglyNumberByDP(int n, int[] primes) {
    return -1;
  }
}
