package com.wxxtest.algorithm.datastructure.queue;

import java.util.Arrays;
import java.util.LinkedList;

public class AsteroidCollision {

  /**
   *
   */
  public int[] asteroidCollision(int[] asteroids) {
    LinkedList<Integer> queue = new LinkedList<>();
    for (int ast : asteroids) {
      //队列为空直接放，前一个行星向左或当前行星向右都不会碰撞直接放入
      if (queue.isEmpty() || queue.peekLast() < 0 || ast > 0) {
        queue.addLast(ast);
        continue;
      }
      //只有prev>0且ast<0的时候才需要处理碰撞的情况
      int absAst = -ast;
      while (!queue.isEmpty()) {
        int prev = queue.peekLast();
        //进处理向右且质量比自己小的行星
        if (prev < 0 || prev >= absAst) {
          break;
        }
        queue.pollLast();
      }
      //所有的都被清理了仅留下自己,或遇到首个方向都向左的行星
      if (queue.isEmpty() || queue.peekLast() < 0) {
        queue.addLast(ast);
        continue;
      }
      //遇到相同质量的同归于尽
      if (queue.peekLast() == absAst) {
        queue.pollLast();
      }
      //要么就是比ast质量大干掉了ast
    }
    int size = queue.size();
    int[] ans = new int[size];
    for (int i = 0; i < size; i++) {
      ans[i] = queue.pollFirst();
    }
    return ans;
  }

  public static void main(String[] args) {
    AsteroidCollision ac = new AsteroidCollision();
    System.out.println(Arrays.toString(
            ac.asteroidCollision(new int[]{5, 10, -5})
    ));

    System.out.println(
            Arrays.toString(
                    ac.asteroidCollision(new int[]{8, -8})
            )
    );
    System.out.println(Arrays.toString(
            ac.asteroidCollision(new int[]{-2, -1, 1, 2})
    ));

    System.out.println(Arrays.toString(
            ac.asteroidCollision(new int[]{-2, -2, 1, -2})
    ));
  }
}
