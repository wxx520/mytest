package com.wxxtest.algorithm.dp.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 三角形的最小路径和，当前节点(i,j)向下走时
 * 只能选择(i+1,j)或(i+1,j+1)
 */
public class Triangle {

  /**
   * f[i][j]为顶层到达[i,j]的最小和
   * 则f[i][j] = triangle[i][j]+min(f[i-1][j],f[i-1][j-1]);
   * <p>
   * 若 m = triangle.size(),
   * n = triangle[k-1].size(), 0<=k<m
   * 考虑边界情况：
   * f[0][0] = triangle[0][0],
   * f[i][0] = triangle[i][0]+f[i-1][0]
   * f[i][n-1] = triangle[i][j]+f[i-1][n-2], 1<=i<m;
   * <p>
   * 则到达最后一层的最小值为
   * ans = min(f[m-1][j]),0<=j<triangle[m].size()
   */
  public int minimumTotal(List<List<Integer>> triangle) {

    int m = triangle.size();
    int n = triangle.get(m - 1).size();
    int[][] f = new int[m][n];
    f[0][0] = triangle.get(0).get(0);

    for (int i = 1; i < m; i++) {
      List<Integer> cL = triangle.get(i);
      f[i][0] = f[i - 1][0] + cL.get(0);

      //当前层的最后一个元素的索引
      int cRIndex = cL.size() - 1;
      //由于上一层的元素个数比当前层少一，
      // 因此只有上一层的最后一个元素能到达下一层的最后一个元素
      f[i][cRIndex] = f[i - 1][cRIndex - 1] + cL.get(cRIndex);
      for (int j = 1; j < cRIndex; j++) {
        f[i][j] = cL.get(j) + Math.min(f[i - 1][j], f[i - 1][j - 1]);
      }
    }

    int ans = Integer.MAX_VALUE;
    for (int v : f[m - 1]) {
      ans = Math.min(ans, v);
    }
    return ans;
  }

  public static void main(String[] args) {
    Triangle t = new Triangle();
    List<List<Integer>> tr1 = new ArrayList<>();
    List<Integer> l1 = new ArrayList<>();
    l1.add(2);
    tr1.add(l1);
    List<Integer> l2 = new ArrayList<>();
    l2.add(3);
    l2.add(4);
    tr1.add(l2);
    List<Integer> l3 = new ArrayList<>();
    l3.add(6);
    l3.add(5);
    l3.add(7);
    tr1.add(l3);
    List<Integer> l4 = new ArrayList<>();
    l4.add(4);
    l4.add(1);
    l4.add(8);
    l4.add(3);
    tr1.add(l4);
    System.out.println(t.minimumTotal(tr1));
  }
}
