package com.wxxtest.algorithm.search.binary;

public class FindMinimumInRotatedSortedArray {

  /**
   * 找到第一次出现左边比自己大，右边比自己小的元素
   */
  public int findMin(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    int left = 0;
    int right = n - 1;
    int mid;
    while (left <= right) {
      //本身已经是降序的
      if (left == right || nums[left] < nums[right]) {
        return nums[left];
      }
      //每次分割会将数组分为，升序和升降升两部分，最小值在升降升那部分里
      mid = (left + right) / 2;
      //先排除mid不是那个左边元素比自己大的情况
      if (mid != 0 && nums[mid - 1] > nums[mid]) {
        return nums[mid];
      }
      //升降升在做半边
      if (nums[left] > nums[mid]) {
        right = mid - 1;
      } else {//否则在右半边
        left = mid + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    FindMinimumInRotatedSortedArray f = new FindMinimumInRotatedSortedArray();
    System.out.println(f.findMin(new int[]{3, 4, 5, 1, 2}));

    System.out.println(f.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));

    System.out.println(f.findMin(new int[]{11, 13, 15, 17}));

    System.out.println(f.findMin(new int[]{3, 1, 2}));
  }

}
