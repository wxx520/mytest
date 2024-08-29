package com.wxxtest.algorithm.sort.mergesort;

/**
 * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
 */
public class ReversePairs {

  public int reversePairs(int[] nums) {
    int len = nums.length;
    if (len <= 1) {
      return 0;
    }
    int[] temp = new int[len];
    return reversePairs(nums, 0, len - 1, temp);
  }

  private int reversePairs(int[] nums, int left, int right, int[] temp) {
    if (right - left < 16) {
      return insertionSort(nums, left, right);
    }
    int mid = left + (right - left) / 2;//向下取整
    //分组
    int leftCount = reversePairs(nums, left, mid, temp);
    int rightCount = reversePairs(nums, mid + 1, right, temp);
    if (nums[mid] <= nums[mid + 1]) {//所以mid+1不会越界
      return leftCount + rightCount;
    }
    int mergeCount = mergeAndCount(nums, left, mid, right, temp);
    //对分组元素进行合并排序，并计算
    return (leftCount + rightCount + mergeCount);
  }

  private int insertionSort(int[] nums, int left, int right) {
    int count = 0;
    for (int i = left + 1; i <= right; i++) {
      int j = i;
      int temp = nums[i];
      while (j > left && nums[j - 1] > temp) {
        nums[j] = nums[j - 1];
        j--;
        count++;
      }
      nums[j] = temp;
    }
    return count;
  }

  public int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
    System.arraycopy(nums, left, temp, left, right - left + 1);
    int i = left;
    int j = mid + 1;
    int k = left;
    int count = 0;
    while (i <= mid && j <= right) {
      if (temp[i] <= temp[j]) {
        nums[k++] = temp[i++];
      } else {
        nums[k++] = temp[j++];
        count += (mid - i) + 1;
      }
    }
    while (i <= mid) {
      nums[k++] = temp[i++];
    }
    while (j <= right) {
      nums[k++] = temp[j++];
    }
    return count;
  }

  public static void main(String[] args) {
    ReversePairs rp = new ReversePairs();
    System.out.println(rp.reversePairs(
            new int[]{7, 5, 6, 4}
    ));

    System.out.println(rp.reversePairs(
            new int[]{9, 7, 5, 4, 6}
    ));
  }
}
