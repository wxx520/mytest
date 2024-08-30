package com.wxxtest.algorithm.datastructure.queue.pq;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/description/">1282. 用户分组</a>
 */
public class PeopleGroup {

    /**
     * 使用hash表，hash表的查询和出入都是O(1)复杂度，
     * 只遍历了一遍groupSizes，因此时间复杂度为O(N)
     */
    public List<List<Integer>> groupThePeopleByHash(int[] groupSizes) {
        //<组的用户数量K，当遇到用户数量相同的组员依次分成数量为K的组>
        Map<Integer, List<List<Integer>>> freqMap = new HashMap<>();
        int n = groupSizes.length;
        for (int curPeople = 0; curPeople < n; curPeople++) {
            int groupSize = groupSizes[curPeople];
            List<List<Integer>> groups = freqMap.getOrDefault(groupSize, new ArrayList<>());
            //当前大小的组还没有成员
            if (groups.size() == 0) {
                List<Integer> curGroup = new ArrayList<>(groupSize);
                curGroup.add(curPeople);
                groups.add(curGroup);
                freqMap.put(groupSize, groups);
                continue;
            }
            List<Integer> lastGroup = groups.get(groups.size() - 1);
            //已经有成员但是规模相同的组已满
            if (lastGroup.size() == groupSize) {
                List<Integer> curGroup = new ArrayList<>(groupSize);
                curGroup.add(curPeople);
                groups.add(curGroup);
            } else {
                //没满就往该组中继续加入当前成员
                lastGroup.add(curPeople);
            }

        }

        List<List<Integer>> ans = new ArrayList<>();
        for (List<List<Integer>> v : freqMap.values()) {
            ans.addAll(v);
        }
        return ans;
    }

    public List<List<Integer>> groupThePeopleByHash1(int[] groupSizes) {
        //<组的用户数量K，当遇到用户数量相同的组员依次分成数量为K的组>
        Map<Integer, List<Integer>> freqMap = new HashMap<>();
        int n = groupSizes.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int curPeople = 0; curPeople < n; curPeople++) {
            int groupSize = groupSizes[curPeople];
            List<Integer> curGroup = freqMap.getOrDefault(groupSize, new ArrayList<>(groupSize));
            //当前大小的组还没有成员
            if (curGroup.size() == 0) {
                curGroup.add(curPeople);
                freqMap.put(groupSize, curGroup);
                continue;
            }
            //已经有成员但是规模相同的组已满
            if (curGroup.size() == groupSize) {
                //将满的加到结果集中
                ans.add(curGroup);

                //当前再用组员数量相同的新组代替
                List<Integer> newGroup = new ArrayList<>(groupSize);
                newGroup.add(curPeople);
                freqMap.put(groupSize, newGroup);
            } else {
                //没满就往该组中继续加入当前成员
                curGroup.add(curPeople);
            }

        }
        //将省下的组加到结果集中就是全部结果
        ans.addAll(freqMap.values());
        return ans;
    }

    /**
     * 使用根据组员数量排序的优先队列，那么组员数量相同的元素有依次相邻的被查出
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int n = groupSizes.length;
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{groupSizes[i], i});
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            int curGroupSize = minHeap.peek()[0];
            List<Integer> curGroup = new ArrayList<>(curGroupSize);
            //测试用例保证有i个组员的组人数是一定刚好的，所以minHeap不会出现空指针的情况
            while (curGroupSize > 0) {
                curGroup.add(minHeap.poll()[1]);
                curGroupSize--;
            }
            ans.add(curGroup);

        }
        return ans;
    }

    public static void main(String[] args) {
        PeopleGroup pg = new PeopleGroup();
        System.out.println(pg.groupThePeopleByHash(new int[]{3, 3, 3, 3, 3, 1, 3}));
        System.out.println(pg.groupThePeopleByHash1(new int[]{3, 3, 3, 3, 3, 1, 3}));
        System.out.println(pg.groupThePeople(new int[]{3, 3, 3, 3, 3, 1, 3}));
        System.out.println(pg.groupThePeopleByHash(new int[]{2, 1, 3, 3, 3, 2}));
        System.out.println(pg.groupThePeopleByHash1(new int[]{2, 1, 3, 3, 3, 2}));
        System.out.println(pg.groupThePeople(new int[]{2, 1, 3, 3, 3, 2}));
    }
}
