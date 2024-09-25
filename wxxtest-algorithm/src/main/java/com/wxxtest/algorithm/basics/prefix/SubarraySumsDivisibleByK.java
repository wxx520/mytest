package com.wxxtest.algorithm.basics.prefix;

/**
 * <a href="https://leetcode.cn/problems/subarray-sums-divisible-by-k/description/?envType=problem-list-v2&envId=5F9Kf6K8">974. 和可被 K 整除的子数组</a>
 */
public class SubarraySumsDivisibleByK {
  public int subarraysDivByK0(int[] nums, int k) {
    int ans = 0;
    int preSum;
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      preSum = 0;
      for (int j = i; j < n; j++) {
        preSum += nums[j];
        if (preSum % k == 0) {
          ans++;
        }
      }
    }
    return ans;
  }

  /**
   * 我们令 P[i]=nums[0]+nums[1]+…+nums[i]。
   * 那么每个连续子数组的和 sum(i,j) 就可以写成 P[j]−P[i−1]（其中 0<i<j）的形式。
   * 此时，判断子数组的和能否被 k 整除就等价于判断 (P[j]−P[i−1])mod k==0，
   * 根据 同余定理，只要 P[j]mod k==P[i−1]mod k，就可以保证上面的等式成立。
   */
  public int subarraysDivByK(int[] nums, int k) {
    int ans = 0;
    // 前缀和出现的次数
    // key: 余数为i，value：出现的次数
    int[] preSumCount = new int[k];
    preSumCount[0] = 1;
    int preSum = 0;
    for (int num : nums) {
      preSum += num;

      //preSum % k 取模可能复数，索引不能为负数
      int remainder = (preSum % k + k) % k;
      int count = preSumCount[remainder];
      ans += count;
      preSumCount[remainder]++;
    }
    return ans;
  }

  public static void main(String[] args) {
    SubarraySumsDivisibleByK k = new SubarraySumsDivisibleByK();
    System.out.println(k.subarraysDivByK0(new int[]{4, 5, 0, -2, -3, 1}, 5));
    System.out.println(k.subarraysDivByK0(new int[]{5}, 9));

    System.out.println(k.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    System.out.println(k.subarraysDivByK(new int[]{5}, 9));
  }
}
