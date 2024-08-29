package com.wxxtest.algorithm.search.backtrack;

import java.util.Arrays;

public class FindArrayGivenSubsetSums {

  public int[] recoverArray(int n, int[] sums) {
    Arrays.sort(sums);
    if (n == 1) {
      return sums;
    }
    return null;
  }

  public static void main(String[] args) {
    FindArrayGivenSubsetSums f = new FindArrayGivenSubsetSums();
    System.out.printf(Arrays.toString(f.recoverArray(3, new int[]{-3, -2, -1, 0, 0, 1, 2, 3})));
    System.out.printf(Arrays.toString(f.recoverArray(2, new int[]{0, 0, 0, 0})));
    System.out.printf(Arrays.toString(f.recoverArray(4, new int[]{0, 0, 5, 5, 4, -1, 4, 9, 9, -1, 4, 3, 4, 8, 3, 8})));

  }
}
