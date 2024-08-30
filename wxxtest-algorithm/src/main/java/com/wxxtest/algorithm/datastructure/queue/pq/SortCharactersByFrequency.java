package com.wxxtest.algorithm.datastructure.queue.pq;

import java.util.*;

/**
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
 * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
 * <a href="https://leetcode.cn/problems/sort-characters-by-frequency/description/">451.根据字符出现频率排序</a>
 */
public class SortCharactersByFrequency {

    public String frequencySortBySort(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        Comparator<Character> comparator = (o1, o2) -> {
            int gap = freqMap.get(o2) - freqMap.get(o1);
            if (gap == 0) {
                return o1.compareTo(o2);
            }
            return gap;
        };
        //也可以自己实现根据频次对数组s.toCharArray进行快速排序
        Character[] cArr = new Character[len];
        for (int i = 0; i < len; i++) {
            cArr[i] = s.charAt(i);
        }

        Arrays.sort(cArr,comparator);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(cArr[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * hash表+快速排序
     * 根据频次排序
     */
    private final static Random random = new Random(System.currentTimeMillis());

    private int[] freq;

    public String frequencySortByMergeSort(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        freq = new int[128];
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            freq[c]++;
        }
        quickSort(charArray, 0, s.length() - 1);
        return new String(charArray);
    }

    private void quickSort(char[] charArray, int left, int right) {
        if (left >= right) {
            return;
        }
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(charArray, left, randomIndex);
        int pivot = charArray[left];
        int lt = left + 1;
        int gt = right;
        //all in freq[left+1..lt)>pivot
        //all in freq[lt..i)=pivot
        //all in freq(gt..right]<pivot
        int i = left + 1;
        while (i <= gt) {
            if (freq[charArray[i]] > freq[pivot]) {
                swap(charArray, i, lt);
                lt++;
                i++;
            } else if (charArray[i] == pivot) {
                i++;
            } else {
                // nums[i] > pivot
                swap(charArray, i, gt);
                gt--;
            }
        }
        swap(charArray, left, lt - 1);
        quickSort(charArray, left, lt - 1);
        quickSort(charArray, gt + 1, right);
    }

    private void swap(char[] charArray, int index1, int index2) {
        char temp = charArray[index1];
        charArray[index1] = charArray[index2];
        charArray[index2] = temp;
    }

    public String frequencySort(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int[] freqArr = new int[128];
        char[] sChars = s.toCharArray();
        for (char c : sChars) {
            freqArr[c]++;
        }

        PriorityQueue<Character> max = new PriorityQueue<>((c1, c2) -> {
            int gap = freqArr[c2] - freqArr[c1];
            if (gap == 0) {
                return c1.compareTo(c2);
            }
            return gap;
        });
        for (char c : sChars) {
            max.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!max.isEmpty()) {
            sb.append(max.poll());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SortCharactersByFrequency sc = new SortCharactersByFrequency();
        System.out.println(sc.frequencySort("tree"));
        System.out.println(sc.frequencySortBySort("tree"));
        System.out.println(sc.frequencySortByMergeSort("tree"));

        System.out.println(sc.frequencySort("cccaaa"));
        System.out.println(sc.frequencySortBySort("cccaaa"));
        System.out.println(sc.frequencySortByMergeSort("cccaaa"));

        System.out.println(sc.frequencySort("Aabb"));
        System.out.println(sc.frequencySortBySort("Aabb"));
        System.out.println(sc.frequencySortByMergeSort("Aabb"));

    }
}
