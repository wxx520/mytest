package com.wxxtest.algorithm.search.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Combine {

  private List<List<Integer>> ans;

  private int n;

  public List<List<Integer>> combine(int n, int k) {
    ans = new ArrayList<>();
    this.n = n;
    if (k == 1 || n == 1) {
      for (int i = 1; i <= n; i++) {
        List<Integer> list = new ArrayList<>();
        list.add(i);
        ans.add(list);
      }
      return ans;
    }
    Deque<Integer> path = new ArrayDeque<>();
    backtrack(path, 1, k);
    return ans;
  }

  /**
   * 遍历 从1-n中选出k个数 的可能性，将一次遍历的结果加入到cur中
   */
  private void backtrack(Deque<Integer> path, int index, int k) {
    if (path.size() == k) {
      ans.add(new ArrayList<>(path));
      return;
    }
    // 只有这里 i <= n - (k - path.size()) + 1 与参考代码 1 不同
    for (int i = index; i <= n - (k - path.size()) + 1; i++) {
      path.addLast(i);
      backtrack(path, i + 1, k);
      path.removeLast();
    }
  }

  public static void main(String[] args) {
    Combine c = new Combine();
    System.out.println(c.combine(4, 2));
    System.out.println(c.combine1(4, 2));
    System.out.println(c.combine1(1, 1));
    System.out.println(c.combine(1, 1));
  }

  List<Integer> path;
  List<List<Integer>> ret;

  public List<List<Integer>> combine1(int n, int k) {
    path = new ArrayList<>();
    ret = new ArrayList<>();
    dfs(1, n, k);
    return ret;
  }

  public void dfs(int cur, int n, int k) {
    if (path.size() + (n - cur + 1) < k) {
      return;
    }
    if (path.size() == k) {
      ret.add(new ArrayList<>(path));
      return;
    }
    path.add(cur);
    dfs(cur + 1, n, k);
    path.remove(path.size() - 1);

    dfs(cur + 1, n, k);
  }
}
