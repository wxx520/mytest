package com.wxxtest.algorithm.basics.loopinvariant;

public class RemoveDuplicates {

  /**
   * 循环不变量，[0,i]为出现不超过两次的元素，[0,j)已经遍历的元素
   * 其中i=0,j=1；freq为nums[i]出现的频次，当j=len时是遍历结束
   * 1、若nums[j]=nums[i]:
   * 1.1 若freq<=1,则freq++,i++,swap(i,j),j++;
   * 1.2 否则freq>1,则j++;
   * 2、若 nums[j]!=nums[i]，则freq = 1, i++,swap(i,j),j++;
   * 最终不超过两次元素数量为i+1
   */
  public int removeDuplicates(int[] nums) {
    int n = nums.length;
    if (n <= 2) {
      return n;
    }
    int i = 0, freq = 1;
    for (int j = 1; j < n; j++) {
      if (nums[j] != nums[i]) {
        freq = 1;
        swap(nums, j, ++i);
        continue;
      }

      if (freq <= 1) {
        freq++;
        swap(nums, j, ++i);
      }
    }
    return i + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    RemoveDuplicates rd = new RemoveDuplicates();
    System.out.println(rd.removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));

    System.out.println(rd.removeDuplicates(
            new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}
    ));
  }
}
