package com.wxxtest.algorithm.datastructure.queue;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/divide-array-in-sets-of-k-consecutive-numbers/description/">1296. 划分数组为连续数字的集合</a>
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {

    /**
     * n = nums.length,
     * hashMap<元素k,元素出现的频率V> 记录每个元素出现的频次
     * 优先队列pq<int[元素，元素出现的频次]> 根据元素大小升序排序
     * deque<int[元素，元素出现的频次]>,
     * 每轮检查k个数，看是否满足单调连续递增的性质，直到 m 轮* k == n,
     * 若中间发现单调连续性不满足，则直接返回false
     * 检查具体为,其中 i= k为需要检查的元素数量，dequeSize为每轮检查之前deque中的元素个数：
     * 当i>0时
     * 1、先取第一个元素，firstNum,
     * 若deque不为空，优先从deque 队首 取，dequeSize--;
     * 否则从pq中取最小值；
     * 将firstNum的出现频次减1即firstNum[1]--，若剩余频次仍>0,
     * 则将firstNum放入的deque队尾(先进先出不破坏deque的有序性)，方便下一轮检查使用
     * 已经检查一个数因此 i--
     * 2、继续取下一个数nextNum，和firstNum对比，
     * 是否满足nextNum[0]-firstNum[0]==1,不满足则直接返回false；
     * 满足则 将firstNum = nextNum，继续取下一个数检查；
     * 取nextNum时，i--, 且 dequeSize>0时优先从deque中取，每次取时dequeSize--；
     * dequeSize=0时从pq中取；
     * 且每取出nextNum时,nextNum[1]--, 若剩余频次仍>0，则将firstNum放入的deque队尾
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (Map.Entry<Integer, Integer> num : freqMap.entrySet()) {
            minHeap.offer(new int[]{num.getKey(), num.getValue()});
        }

        LinkedList<int[]> deque = new LinkedList<>();
        int dequeSize = 0, i = k;
        int[] firstNum, nextNum;
        while (!minHeap.isEmpty() || !deque.isEmpty()) {
            dequeSize = deque.size();
            //首個元素要么从队列头部开始取，要么从minHeap中取出最小值
            if (dequeSize > 0) {
                firstNum = deque.pollFirst();
                dequeSize--;
            } else {
                firstNum = minHeap.poll();
            }

            //每次使用一个数，该数出现的频次就要减一，且没有使用完的依次放入队列尾部
            freqMinusOne(deque, firstNum);
            //一轮的循环次数也要减1
            i--;

            while (i > 0) {
                //后面的元素优先从队列中取
                if (dequeSize > 0) {

                    nextNum = deque.pollFirst();
                    dequeSize--;

                    //单调连续性验证
                    if (nextNum[0] - firstNum[0] != 1) {
                        return false;
                    }

                    //更新当前元素为上一个元素
                    firstNum = nextNum;

                    //每次使用一个数，该数出现的频次就要减一，且没有使用完的依次放入队列尾部
                    freqMinusOne(deque, nextNum);
                    i--;
                    continue;
                }

                //队列取完了才从堆中拿
                if (minHeap.isEmpty()) {
                    return false;
                }
                nextNum = minHeap.poll();
                if (nextNum[0] - firstNum[0] != 1) {
                    return false;
                }


                firstNum = nextNum;
                freqMinusOne(deque, nextNum);
                i--;
            }

            //一轮结束i重置为k，继续为下一轮准备
            i = k;
        }
        return true;
    }

    private void freqMinusOne(LinkedList<int[]> deque, int[] nextNum) {
        nextNum[1]--;
        if (nextNum[1] > 0) {
            deque.offerLast(nextNum);
        }
    }

    /**
     * 贪心算法
     */
    public boolean isPossibleDivideByGreedy(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        for (int x : nums) {
            //不存在的原因只能是频次使用完了
            if (!cnt.containsKey(x)) {
                continue;
            }
            for (int j = 0; j < k; j++) {
                int num = x + j;
                if (!cnt.containsKey(num)) {
                    return false;
                }
                cnt.put(num, cnt.get(num) - 1);
                if (cnt.get(num) == 0) {
                    cnt.remove(num);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        DivideArrayInSetsOfKConsecutiveNumbers d = new DivideArrayInSetsOfKConsecutiveNumbers();
        System.out.println(d.isPossibleDivide(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4));
        System.out.println(d.isPossibleDivide(new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3));
        System.out.println(d.isPossibleDivide(new int[]{1, 2, 3, 4}, 3));
        System.out.println(d.isPossibleDivide(new int[]{1, 1, 2, 2, 3, 3}, 2));
        System.out.println(d.isPossibleDivide(new int[]{1, 1, 1, 2, 3, 3}, 3));

        System.out.println(d.isPossibleDivideByGreedy(new int[]{1, 1, 1, 2, 3, 3}, 3));
    }
}
