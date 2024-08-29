package com.wxxtest.algorithm.search.binary;

public class BinarySearch {

  public int search(int[] nums, int target) {
    return binarySearch(nums, target, 0, nums.length - 1);
  }

  private int binarySearch(int[] arr, int target, int left, int right) {
    if (left > right) {
      return -1;
    }
    int mid = (left + right) / 2;
    if (target == arr[mid]) {
      return mid;
    } else if (target > arr[mid]) {
      return binarySearch(arr, target, mid + 1, right);
    } else {
      return binarySearch(arr, target, left, mid - 1);
    }
  }
}
