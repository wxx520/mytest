package com.wxxtest.algorithm.datastructure.queue.pq;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <a href="https://leetcode.cn/problems/top-k-frequent-elements/description/">347.前 K 个高频元素</a>
 * 1、可以使用优先队列，取前k个频次较高的数
 * 2、也可以用快速排序的子过程，根据频次排序，找到第k大的频次
 */
public class TopKFrequentElements {

    /**
     * 使用优先队列
     */
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            //统计每个数字出现的频次
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        //根据频次升序排列
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int freq = entry.getKey();
            int num = entry.getValue();
            if (minHeap.size() < k) {
                minHeap.offer(new int[]{freq, num});
                continue;
            }
            if (freq > minHeap.peek()[0]) {
                minHeap.poll();
                minHeap.offer(new int[]{freq, num});
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = minHeap.poll()[1];
        }
        return ans;
    }

    public int[] topKFrequentByQuickSort(int[] nums, int k) {
        // 步骤 1：统计每一个元素出现的次数
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 步骤 2：把元素的值和频数绑定，放在一个数组中
        // 因为不知道有多少个不同元素，所以用动态数组
        List<int[]> values = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            values.add(new int[]{num, count});
        }

        // 步骤 3：在 values 上执行 partition 找到下标 k - 1 的位置，当 left 与 right 重合的时候，前 k 个元素就是题目要求的结果
        int left = 0;
        int right = values.size() - 1;
        int target = k - 1;
        while (left <= right) {
            int pIndex = partition(values, left, right);
            if (pIndex < target) {
                left = pIndex + 1;
            } else if (pIndex > target) {
                right = pIndex - 1;
            } else {
                break;
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = values.get(i)[0];
        }
        return res;

    }

    public int partition(List<int[]> values, int left, int right) {
        int randomIndex = (int) (Math.random() * (right - left + 1)) + left;
        Collections.swap(values, randomIndex, left);

        int pivot = values.get(left)[1];
        int j = left;
        // 循环不变量定义：
        // [left + 1.. j] >= pivot
        // (j, i) < pivot
        // 出现频率前 k 高的元素，降序排序，下标是 k - 1
        for (int i = left + 1; i <= right; i++) {
            if (values.get(i)[1] >= pivot) {
                j++;
                Collections.swap(values, j, i);
            }
        }
        Collections.swap(values, left, j);
        return j;
    }

    public static void main(String[] args) {
        TopKFrequentElements tk = new TopKFrequentElements();
        System.out.println(Arrays.toString(tk.topKFrequent(
                        new int[]{1, 1, 1, 2, 2, 3},
                        2
                )
        ));
        System.out.println(Arrays.toString(tk.topKFrequent(
                        new int[]{1},
                        1
                )
        ));
        System.out.println(Arrays.toString(tk.topKFrequentByQuickSort(
                        new int[]{1, 1, 1, 2, 2, 3},
                        2
                )
        ));
        System.out.println(Arrays.toString(tk.topKFrequentByQuickSort(
                        new int[]{-1, -1},
                        1
                )
        ));
    }
}
