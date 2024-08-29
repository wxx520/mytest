package com.wxxtest.algorithm.dp.lis;

import java.util.Arrays;

/**
 * <p>你打算构建一些障碍赛跑路线。给你一个 下标从 0 开始 的整数数组 obstacles ，数组长度为 n ，其中 obstacles[i] 表示第 i 个障碍的高度。
 * 对于每个介于 0 和 n - 1 之间（包含 0 和 n - 1）的下标  i ，在满足下述条件的前提下，请你找出 obstacles 能构成的最长障碍路线的长度：
 * 你可以选择下标介于 0 到 i 之间（包含 0 和 i）的任意个障碍。
 * 在这条路线中，必须包含第 i 个障碍。
 * 你必须按障碍在 obstacles 中的 出现顺序 布置这些障碍。
 * 除第一个障碍外，路线中每个障碍的高度都必须和前一个障碍 相同 或者 更高 。
 * 返回长度为 n 的答案数组 ans ，其中 ans[i] 是上面所述的下标 i 对应的最长障碍赛跑路线的长度。
 */
public class FindTheLongestValidObstacleCourseAtEachPosition {

  /**
   * 定义f(i),以第i个障碍物结尾的最长递增子序列数量，则由
   * f(i+1) = max(f(j))+1, ob[j]<=ob[i], 0<=j<=i
   *
   * @param obstacles 原始障碍物数组
   * @return 以第i个障碍物结尾的最长障碍物数量
   */
  public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
    int n = obstacles.length;
    int[] f = new int[n];
    for (int i = 0; i < n; i++) {
      f[i] = 1;
      for (int j = 0; j < i; j++) {
        if (obstacles[i] >= obstacles[j]) {
          f[i] = Math.max(f[i], f[j] + 1);
        }
      }
    }
    return f;
  }

  /**
   * 定义f(i),以第i个障碍物结尾的最长递增子序列数量，则由
   * f(i+1) = max(f(j))+1, ob[j]<=ob[i], 0<=j<=i
   * <p>
   * 定义辅助函数d(x),表示长度为x的递增子序列的最后一个最小的值y
   * 可以发现y关于i递增, 反正法可证，假设d(i)<d(j),i>j, 则d(i)减去
   * 长度为i-j后达到一个新的长度为j子序列d1(j)则d1(j)<=d(i)<d(j),
   * 那么我们就找到了一个长度为 j的最长上升子序列，并且末尾元素比 d[j] 小，从而产生了矛盾。
   * 因此单调性得证明，令d[1] = obstacles[0], len为d的最大值，遍历obstacles，则：
   * 若 obstacles[j]>=d[len],x = ++len,d[x] = obstacles[j];
   * 否则找到最大的x使得 d[x-1]<=obstacles[j]<d[x]，其中1<=x-1<len
   * d[x] = obstacles[j];
   * 其中x就是当前元素obstacles[j]产生的最长子序列的长度
   * <p>
   * 因此我们遍历obstacles，当遍历到第i个元素时，对于d对于obstacles[i]产生的
   *
   * @param obstacles 原始障碍物数组
   * @return 以第i个障碍物结尾的最长障碍物数量
   */
  public static int[] longestObstacleCourseAtEachPositionByMonotonyAndBinary(int[] obstacles) {
    int n = obstacles.length;
    int[] f = new int[n];
    f[0] = 1;
    int[] auxF = new int[n + 1];
    auxF[1] = obstacles[0];
    int len = 1;
    for (int i = 1; i < n; i++) {
      f[i] = 1;
      int curObs = obstacles[i];
      if (curObs >= auxF[len]) {
        auxF[++len] = curObs;
        f[i] = len;
      } else {
        int l = 1, r = len, pos = 0;
        while (l <= r) {
          int mid = (l + r) >> 1;
          if (auxF[mid] <= curObs) {
            pos = mid;
            l = mid + 1;
          } else {
            r = mid - 1;
          }
        }
        auxF[pos + 1] = curObs;
        f[i] = pos + 1;
      }
    }
    return f;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(longestObstacleCourseAtEachPosition(new int[]{1, 2, 3, 2})));

    System.out.println(Arrays.toString(longestObstacleCourseAtEachPositionByMonotonyAndBinary(new int[]{1, 2, 3, 2})));
  }
}
