package com.wxxtest.algorithm.basics.recursion;

public class MyPow {
  public double myPow(double x, int n) {
    if (n == 1) {
      return x;
    }
    if (n == 0) {
      return 1;
    }

    if (n > 0) {
      return n % 2 == 1 ? x * myPow(x * x, (n - 1) / 2) : myPow(x * x, n / 2);
    }

    double ans = 1 / x;
    return ans / myPow(x, -(n + 1));
  }

  public double myPow1(double x, int n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return x;
    }
    if (n < 0) {
      return 1 / (x * myPow1(x, -(n + 1)));
    }
    double ans = 1.0;
    double x_contribute = x;
    while (n > 0) {
      if (n % 2 == 1) {
        ans *= x_contribute;
      }
      x_contribute *= x_contribute;
      n = n / 2;
    }
    return ans;
  }


}
