package com.wxxtest.algorithm.datastructure.queue.pq.heap;

import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <a href="https://leetcode.cn/studyplan/leetcode-75/">数组中第k大的元素</a>
 */
public class FindKthLargest {

  /**
   * 此处时间复杂度为n*logK,
   * 使用快排的思想，减而治之，不断缩小问题规模，可以降低为O(N)
   */
  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int i = 0; i < k; i++) {
      minHeap.offer(nums[i]);
    }
    for (int i = k; i < nums.length; i++) {
      int topElement = nums[i];
      if (topElement > minHeap.peek()) {
        minHeap.poll();
        minHeap.offer(topElement);
      }
    }
    return minHeap.peek();
  }

  public static void main(String[] args) {
    FindKthLargest fk = new FindKthLargest();
    System.out.println(fk.findKthLargest(
            new int[]{3,2,1,5,6,4},2
    ));
  }
}
