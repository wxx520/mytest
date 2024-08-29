package com.wxxtest.algorithm.dp.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 俄罗斯套娃
 */
public class RussianDollEnvelopes {

  /**
   * 每次套娃，多余的空间越小，下一次被套的可能性就越大(宽不变，高最小；高不变，宽最小)
   *
   * @param envelopes 娃娃数组
   * @return 最大套娃数量
   */
  public static int maxEnvelopes(int[][] envelopes) {
    int n = envelopes.length;
    // 有限选择第二数更小的数对，为接下来的链路留下更多的空间，curr为前一个数对的第二个数
    int ans = 1;
    //如果a[1]相同则a[0]逆序排，否则根据第一个元素从大到小排列
    Arrays.sort(envelopes, (a, b) -> (a[1] - b[1] == 0) ? (b[0] - a[0]) : (a[1] - b[1]));
    //以第i个信封结尾的最大信封数量
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      int[] cur = envelopes[i];
      for (int j = i - 1; j >= 0; j--) {
        //没有逆序，表示第二个元素一定不相等，即e[i]>e[j]
        if (cur[0] > envelopes[j][0]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      ans = Math.max(ans, dp[i]);
    }
    return ans;
  }

  public static int maxEnvelopesByMonotonyAndBinary(int[][] envelopes) {
    int n = envelopes.length;
    Arrays.sort(envelopes, (o1, o2) -> {
      if (o1[0] == o2[0]) {
        return o2[1] - o1[1];
      } else {
        return o1[0] - o2[0];
      }
    });
    List<Integer> f = new ArrayList<>();
    f.add(envelopes[0][1]);
    for (int i = 1; i < n; i++) {
      int curNum = envelopes[i][1];
      if (curNum > f.get(f.size() - 1)) {
        f.add(curNum);
      } else {
        int low = 0, high = f.size() - 1;
        while (low < high) {
          int mid = (high - low) / 2 + low;
          if (curNum > f.get(mid)) {
            low = mid + 1;
          } else {
            high = mid;
          }
        }
        f.set(low, curNum);
      }
    }
    return f.size();
  }

  public static void main(String[] args) {
    System.out.println(maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 4}, {2, 4}}));

    System.out.println(maxEnvelopesByMonotonyAndBinary(new int[][]{{5, 4}, {6, 4}, {6, 4}, {2, 4}}));

  }
}
