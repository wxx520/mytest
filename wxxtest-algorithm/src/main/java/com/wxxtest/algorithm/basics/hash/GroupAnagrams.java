package com.wxxtest.algorithm.basics.hash;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked">49. 字母异位词分组</a>
 */
public class GroupAnagrams {

  public List<List<String>> groupAnagrams(String[] strs) {
    int n = strs.length;
    if (n == 0) {
      return new ArrayList<>();
    } else if (n == 1) {
      List<List<String>> ans = new ArrayList<>();
      List<String> list = new ArrayList<>();
      list.add(strs[0]);
      ans.add(list);
      return ans;
    }
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {

      int[] count = new int[26];
      for (int i = 0; i < s.length(); i++) {
        count[s.charAt(i) - 'a']++;
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 26; i++) {
        if (count[i] != 0) {
          sb.append(i + 'a');
          sb.append(count[i]);
        }
      }
      String key = sb.toString();
      List<String> list = map.getOrDefault(key, new ArrayList<>());
      list.add(s);
      map.put(key, list);
    }
    return new ArrayList<>(map.values());
  }

  public List<List<String>> groupAnagramsBySort(String[] strs) {
    int n = strs.length;
    if (n == 0) {
      return new ArrayList<>();
    } else if (n == 1) {
      List<List<String>> ans = new ArrayList<>();
      List<String> list = new ArrayList<>();
      list.add(strs[0]);
      ans.add(list);
      return ans;
    }
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      String key = new String(chars);
      List<String> list = map.getOrDefault(key, new ArrayList<>());
      list.add(str);
      map.put(key, list);
    }
    return new ArrayList<>(map.values());
  }


  public static void main(String[] args) {
    GroupAnagrams g = new GroupAnagrams();
    System.out.println(g.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    System.out.println(g.groupAnagrams(new String[]{""}));
    System.out.println(g.groupAnagrams(new String[]{"a"}));

    System.out.println(g.groupAnagramsBySort(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    System.out.println(g.groupAnagramsBySort(new String[]{""}));
    System.out.println(g.groupAnagramsBySort(new String[]{"a"}));

  }
}
