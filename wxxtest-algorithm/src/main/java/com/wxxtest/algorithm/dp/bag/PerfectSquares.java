package com.wxxtest.algorithm.dp.bag;

public class PerfectSquares {


  /**
   * f(i)表示平方和的最少数量，
   * 则f(i) = min(f(i-j^2)+1),1<=j<=i的开方
   *
   * @param n 平方和
   * @return 组成平方和的最少数量
   */
  public int numSquares(int n) {
    if (n <= 3) {
      return n;
    } else if (n == 4) {
      return 1;
    } else if (n == 5) {
      return 2;
    } else if (n == 6) {
      return 3;
    }
    int[] f = new int[n + 1];
    f[1] = 1;
    f[2] = 2;
    f[3] = 3;
    f[4] = 1;
    f[5] = 2;
    f[6] = 3;
    for (int i = 7; i <= n; i++) {
      int minn = Integer.MAX_VALUE;
      for (int j = 1; j * j <= i; j++) {
        minn = Math.min(minn, f[i - j * j]);
      }
      f[i] = minn + 1;
    }
    return f[n];
  }

  public int numSquaresByMathTheory(int n) {
    if (n <= 3) {
      return n;
    }
    if (isPerfectSquare(n)) {
      return 1;
    }

    if (checkAnswer4(n)) {
      return 4;
    }

    for (int i = 1; i <= (int) Math.sqrt(n); i++) {
      if (isPerfectSquare(n - i * i)) {
        return 2;
      }
    }
    return 3;
  }

  public boolean isPerfectSquare(int x) {
    int sqt = (int) Math.sqrt(x);
    return sqt * sqt == x;
  }

  public boolean checkAnswer4(int x) {
    while (x % 4 == 0) {
      x = x / 4;
    }
    return x % 8 == 7;
  }

  public static void main(String[] args) {
    PerfectSquares ps = new PerfectSquares();
    System.out.println(ps.numSquares(12));
    System.out.println(ps.numSquares(13));
    System.out.println(ps.numSquaresByMathTheory(13));
  }
}
