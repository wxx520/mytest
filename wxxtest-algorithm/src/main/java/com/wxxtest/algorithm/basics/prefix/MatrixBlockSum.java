package com.wxxtest.algorithm.basics.prefix;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/matrix-block-sum/?envType=problem-list-v2&envId=5F9Kf6K8">1314. 矩阵区域和</a>
 */
public class MatrixBlockSum {

  public int[][] matrixBlockSum(int[][] mat, int k) {
    return null;
  }

  public static void main(String[] args) {
    MatrixBlockSum s = new MatrixBlockSum();
    printArray(s.matrixBlockSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1));
    printArray(s.matrixBlockSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 2));
  }

  private static void printArray(int[][] arr) {
    System.out.print("{");
    for (int[] a : arr) {
      System.out.print(Arrays.toString(a));
    }
    System.out.println("}");
  }
}
