package com.wxxtest.algorithm.dp.tree;

public class UniqueBST {

  public static int numTrees(int n) {
    if (n <= 2) {
      return n;
    }
    int[] G = new int[n + 1];
    G[0] = 1;
    G[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        G[i] += G[j - 1] * G[i - j];
      }
    }

    return G[n];
  }

  public static int numTreesByMath(int n) {
    long C = 1;
    for (int i = 0; i < n; i++) {
      C = C * 2 * (2 * i + 1) / (i + 2);
    }
    return (int) C;
  }

  public static void main(String[] args) {
    System.out.println(numTrees(3));
    System.out.println(numTrees(1));
    System.out.println(numTrees(5));
    System.out.println(numTreesByMath(5));
  }
}
