package com.wxxtest.algorithm.dp.dimension.one;

/**
 * 给你一个下标从 0 开始的二维整数数组 questions ，其中 questions[i] = [pointI, brainpowerI] 。
 * <p>
 * 这个数组表示一场考试里的一系列题目，你需要 按顺序 （也就是从问题 0 开始依次解决），针对每个问题选择 解决 或者 跳过 操作。解决问题 i 将让你 获得  pointI 的分数，但是你将 无法 解决接下来的 brainpowerI 个问题（即只能跳过接下来的 brainpowerI 个问题）。如果你跳过问题 i ，你可以对下一个问题决定使用哪种操作。
 * <p>
 * 比方说，给你 questions = [[3, 2], [4, 3], [4, 4], [2, 5]] ：
 * 如果问题 0 被解决了， 那么你可以获得 3 分，但你不能解决问题 1 和 2 。
 * 如果你跳过问题 0 ，且解决问题 1 ，你将获得 4 分但是不能解决问题 2 和 3 。
 * 请你返回这场考试里你能获得的 最高 分数。
 */
public class SolvingQuestionsWithBrainpower {

  /**
   * f[i]表示解决第 i 道题目及以后的题目可以获得的最高分数，则
   * f[n-1]=q[n-1][1]
   * f[i] = max(f[i+1], dp[i+q[i][1]+1]+q[i][0]);
   *
   * @param questions 给你一个下标从 0 开始的二维整数数组 questions ，其中 questions[i] = [得分, 接下来跳过的问题数] 。
   * @return 按照规则回答智力问题后能够拿到累积最大得分
   */
  public static long mostPoints(int[][] questions) {
    //当前问题过后下一个问题可以选择解决或者忽略
    int n = questions.length;
    long[] f = new long[n];
    f[n - 1] = questions[n - 1][0];
    for (int i = n - 2; i >= 0; i--) {
      int[] curQ = questions[i];
      if (curQ[1] + i + 1 >= n) {
        f[i] = Math.max(f[i + 1], curQ[0]);
      } else {
        f[i] = Math.max(f[i + 1], curQ[0] + f[i + curQ[1] + 1]);
      }
    }
    return f[0];
  }

  public static void main(String[] args) {
    System.out.println(mostPoints(new int[][]{{3, 2}, {4, 3}, {4, 4}, {2, 5}}));
    System.out.println(mostPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}}));
  }
}
