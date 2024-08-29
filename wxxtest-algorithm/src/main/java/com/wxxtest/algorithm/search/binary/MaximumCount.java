package com.wxxtest.algorithm.search.binary;

public class MaximumCount {

  private int pos;
  private int neg;

  public int maximumCount(int[] nums) {
    pos = 0;
    neg = 0;
    binarySearch(nums, 0, nums.length - 1);
    return Math.max(pos, neg);
  }

  private void binarySearch(int[] nums, int l, int r) {
    if (l > r) {
      return;
    }
    int mid = (l + r) >> 1;
    int midNum = nums[mid];
    if (midNum < 0) {
      neg += mid - l + 1;
      binarySearch(nums, mid + 1, r);
    } else if (midNum == 0) {
      binarySearch(nums, l, mid - 1);
      binarySearch(nums, mid + 1, r);
    } else {
      pos += r - mid + 1;
      binarySearch(nums, l, mid - 1);
    }
  }

  public static void main(String[] args) {
    MaximumCount mc = new MaximumCount();
    System.out.println(mc.maximumCount(new int[]{-2, -1, -1, 1, 2, 3}));
  }
}
