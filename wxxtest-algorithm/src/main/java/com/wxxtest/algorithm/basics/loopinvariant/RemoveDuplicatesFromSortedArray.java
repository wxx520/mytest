package com.wxxtest.algorithm.basics.loopinvariant;

public class RemoveDuplicatesFromSortedArray {

  /**
   * 从左到右遍历整个数组，定义
   * [0,i]为没有重复数组
   * [0,j]为已经遍历的数组
   * 其中i从0开始，j从1开始，当j=len-1时，遍历结束，最终结果为i
   * 遍历过程中，若发现nums[j]!=nums[j-1],i++，j++;
   * 否则直接遍历下一个，即j++;
   */
  public int removeDuplicates(int[] nums) {
    int len = nums.length;
    if (len <= 1) {
      return len;
    }
    int i = 0;
    for (int j = 1; j < len; j++) {
      if (nums[j] != nums[i]) {
        nums[++i] = nums[j];
      }
    }
    return i+1;
  }

  public static void main(String[] args) {
    RemoveDuplicatesFromSortedArray r = new RemoveDuplicatesFromSortedArray();
    System.out.println(r.removeDuplicates(new int[]{1, 1, 2}));
  }
}
