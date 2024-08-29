package com.wxxtest.algorithm.sort.mergesort;

import java.util.ArrayList;
import java.util.List;

public class CountSmaller {

  private int[] index;
  private int[] helper;
  private int[] count;

  public List<Integer> countSmaller(int[] nums) {
    List<Integer> res = new ArrayList<>(nums.length);

    //根据索引对应的大小对索引进行排序
    index = new int[nums.length];
    helper = new int[nums.length];
    count = new int[nums.length];
    for (int i = 0; i < index.length; i++) {
      index[i] = i;
    }

    merge(nums, 0, nums.length - 1);

    for (int i = 0; i < count.length; i++) {
      res.add(i, count[i]);
    }
    return res;
  }

  private void merge(int[] nums, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) >> 1;

    merge(nums, left, mid);
    merge(nums, mid + 1, right);
    if (nums[index[mid]] <= nums[index[mid + 1]]) {
      return;
    }

    int i = left;
    int j = mid + 1;
    int hi = left;
    while (i <= mid && j <= right) {
      if (nums[index[i]] <= nums[index[j]]) {
        helper[hi++] = index[i++];
      } else {
        count[index[i]] += right - j + 1;
        helper[hi++] = index[j++];
      }

    }
    while (i <= mid) {
      helper[hi++] = index[i++];
    }
    while (j <= right) {
      helper[hi++] = index[j++];
    }

    System.arraycopy(helper, left, index, left, right + 1 - left);
  }

  public static void main(String[] args) {
    CountSmaller cs = new CountSmaller();
    System.out.println(cs.countSmaller(new int[]{5, 2, 6, 1}));
  }
}
