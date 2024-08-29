package com.wxxtest.algorithm.datastructure.queue;

import java.util.*;

public class RobotCollisions {

  /**
   * 从左向右遍历所有的位置的机器人，
   * 1、如果机器人向左或者前一个机器人向右当前机器人也向右，不会发生碰撞
   * 2、只有前一个机器人prevR向右当前机器人curL向左才会发生碰撞
   * 2.1 curl不断干掉前面向右且健康度比自己低的机器人
   * 并且自己的健康度也逐渐下降
   * 2.2 直到前面的健康度低向右的机器人都被干掉
   * 2.3 或者遇到向右健康度一样的机器人同归于尽
   * 2.4 或者被比自己健康度高向右的机器人干掉
   * 最终剩下来的机器人就是幸存者
   */
  public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
    int n = positions.length;
    List<Integer> ans = new ArrayList<>();
    if (n == 1) {
      ans.add(healths[0]);
      return ans;
    }
    //机器人的位置和健康度方向信息映射信息
    LinkedHashMap<Integer, int[]> robotsMap = new LinkedHashMap<>(n);
    for (int i = 0; i < n; i++) {
      robotsMap.put(positions[i], new int[]{healths[i], directions.charAt(i) == 'L' ? -1 : 1});
    }

    Arrays.sort(positions);
    LinkedList<Integer> posQueue = new LinkedList<>();
    int[] curR;//当前机器人的健康度和方向信息
    int[] prevR;
    for (int pos : positions) {
      curR = robotsMap.get(pos);
      if (posQueue.isEmpty() || curR[1] == 1
              || robotsMap.get(posQueue.peekLast())[1] == -1) {
        posQueue.add(pos);
        continue;
      }

      //只有前一个机器人向右且当前机器人向左才需要处理碰撞的情况
      while (!posQueue.isEmpty()) {
        prevR = robotsMap.get(posQueue.peekLast());
        if (prevR[1] == -1 || prevR[0] >= curR[0]) {
          break;
        }
        //撞掉一个机器人健康度减一
        curR[0]--;
        posQueue.pollLast();
      }

      //要么之前的向右机器人健康度较低全部被撞掉，要么只剩下向左的机器人
      if (posQueue.isEmpty() ||
              robotsMap.get(posQueue.peekLast())[1] == -1) {
        posQueue.add(pos);
        continue;
      }

      //要么健康度一样同归于尽
      prevR = robotsMap.get(posQueue.peekLast());
      if (prevR[0] == curR[0]) {
        posQueue.pollLast();
        continue;
      }

      //要么前一个健康度跟高撞掉curR,前一个机器人的健康度减一
      prevR[0]--;
    }
    Set<Integer> set = new HashSet<>(posQueue);
    //按照原来的位置遍历位置，如果位置存在于幸存队列中
    //则将该位置机器人对应的健康度加入到结果集中
    for (Map.Entry<Integer, int[]> r : robotsMap.entrySet()) {
      if (set.contains(r.getKey())) {
        ans.add(r.getValue()[0]);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    RobotCollisions rc = new RobotCollisions();
    System.out.println(rc.survivedRobotsHealths(
            new int[]{5,4,3,2,1},
            new int[]{2,17,9,15,10},
            "RRRRR"
    ));
    System.out.println(rc.survivedRobotsHealths(
            new int[]{3,5,2,6},
            new int[]{10,10,15,12},
            "RLRL"
    ));
    System.out.println(rc.survivedRobotsHealths(
            new int[]{1,2,5,6},
            new int[]{10,10,11,11},
            "RLRL"
    ));
  }
}
