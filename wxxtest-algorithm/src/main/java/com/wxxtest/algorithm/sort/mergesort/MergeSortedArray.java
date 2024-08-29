package com.wxxtest.algorithm.sort.mergesort;

import java.util.Arrays;

public class MergeSortedArray {

  /**
   * 循环不变量，[i,m+n-1]为已经排好序的数组
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int index = m + n - 1;
    int i = m - 1;
    int j = n - 1;
    while (i >= 0 && j >= 0) {
      if (nums2[j] >= nums1[i]) {
        nums1[index--] = nums2[j--];
      } else {
        nums1[index--] = nums1[i--];
      }
    }

    while (j >= 0) {
      nums1[index--] = nums2[j--];
    }
  }

  public static void main(String[] args) {
    MergeSortedArray m = new MergeSortedArray();
    int[] n1 = new int[]{1, 2, 3, 0, 0, 0};
    int[] n2 = new int[]{2, 5, 6};
    m.merge(n1, 3, n2, 3);
    System.out.println(Arrays.toString(n1));
  }
}
