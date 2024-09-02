package com.wxxtest.algorithm.datastructure.queue.pq;

import java.util.*;

/**
 * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，
 * 并且是一个整数 k ，返回离原点 (0,0) 最近的 k 个点。
 * <p>
 * 这里，平面上两点之间的距离是 欧几里德距离（ √(x1 - x2)2 + (y1 - y2)2 ）。
 * <p>
 * 你可以按 任何顺序 返回答案。除了点坐标的顺序之外，答案 确保 是 唯一 的。
 * <a href="https://leetcode.cn/problems/k-closest-points-to-origin/">973.最接近原点的 K 个点</a>
 */
public class KClosestPointsToOrigin {

    /**
     * 使用优先队列，根据点到原点的距离进行排序
     * 使用最大堆，堆的大小为k+1，最小的k个点就是目标
     * 时间复杂度为O(nlgK)
     */
    public int[][] kClosest(int[][] points, int k) {
        int len = points.length;
        if (len == 0) {
            return new int[0][0];
        }
        int[][] res = new int[k][2];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(k + 1, (o1, o2) -> (
                (o2[0] * o2[0] + o2[1] * o2[1]) - (o1[0] * o1[0] + o1[1] * o1[1])
        ));
        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll();
        }
        return res;
    }

    /**
     * 将数组points根据距离升序排序，取前k个即目标
     * O(NlgN)
     */
    public int[][] kClosestBySort(int[][] points, int k) {
        int len = points.length;
        if (len == 0) {
            return new int[0][0];
        }
        Comparator<int[]> comparator = Comparator.comparingInt(o -> (o[0] * o[0] + o[1] * o[1]));

        Arrays.sort(points, comparator);

        int[][] res = new int[k][2];
        System.arraycopy(points, 0, res, 0, k);
        return res;
    }

    /**
     * 将数组points根据距离做快速排序的子过程，当找到第k小的值时，取前k个即目标
     * O(N)
     */
    public int[][] kClosestByQuickSort(int[][] points, int k) {
        int len = points.length;
        if (len == 0) {
            return new int[0][0];
        }
        int left = 0;
        int right = len - 1;
        int target = k - 1;
        while (true) {
            int partitionIndex = partition(points, left, right);
            if (partitionIndex == target) {
                break;
            } else if (partitionIndex > target) {
                right = partitionIndex - 1;
            } else {
                left = partitionIndex + 1;
            }
        }
        int[][] res = new int[k][2];
        System.arraycopy(points, 0, res, 0, k);
        return res;
    }

    private static final Random random = new Random(System.currentTimeMillis());

    private int partition(int[][] points, int left, int right) {
        if (left >= right) {
            return left;
        }
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(points, randomIndex, left);

        int[] lP = points[left];
        int lpDistance = lP[0] * lP[0] + lP[1] * lP[1];
        int i = left + 1;
        int j = right;
        //双路快排循环不变量
        //(left,i)<=lPDis,i=left+1,j = right;
        //(j,right]>lPDis
        while (i <= j) {
            while (i <= right && points[i][0] * points[i][0] + points[i][1] * points[i][1] <= lpDistance) {
                i++;
            }
            while (i <= j && points[i][0] * points[i][0] + points[i][1] * points[i][1] > lpDistance) {
                swap(points, i, j);
                j--;
            }
        }
        swap(points, j, left);
        return j;
    }

    private void swap(int[][] nums, int i, int j) {
        int[] temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin k = new KClosestPointsToOrigin();
        System.out.println(printlnArray(k.kClosest(
                new int[][]{{1, 3}, {-2, 2}},
                1
        )));
        System.out.println(printlnArray(k.kClosestBySort(
                new int[][]{{1, 3}, {-2, 2}},
                1
        )));
        System.out.println(printlnArray(k.kClosestByQuickSort(
                new int[][]{{1, 3}, {-2, 2}},
                1
        )));

        System.out.println(printlnArray(k.kClosest(
                new int[][]{{3, 3}, {5, -1}, {-2, 4}},
                2
        )));
        System.out.println(printlnArray(k.kClosestBySort(
                new int[][]{{3, 3}, {5, -1}, {-2, 4}},
                2
        )));
        System.out.println(printlnArray(k.kClosestByQuickSort(
                new int[][]{{3, 3}, {5, -1}, {-2, 4}},
                2
        )));

    }

    public static String printlnArray(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int[] a : arr) {
            sb.append("(");
            sb.append(a[0]);
            sb.append(",");
            sb.append(a[1]);
            sb.append("), ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();

    }
}
