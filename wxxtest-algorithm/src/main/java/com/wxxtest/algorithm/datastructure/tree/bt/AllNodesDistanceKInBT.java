package com.wxxtest.algorithm.datastructure.tree.bt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AllNodesDistanceKInBT {

  private List<Integer> ans;

  private Map<Integer, TreeNode> parents;

  /**
   * 深度优先遍历反向记录各个节点的父节点
   * <p>
   * 然后找出target的k级父节点以及子节点
   */
  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    ans = new LinkedList<>();
    parents = new HashMap<>();
    // 从 root 出发 DFS，记录每个结点的父结点
    findParents(root);

    // 从 target 出发 DFS，寻找所有深度为 k 的结点
    findAns(target, null, 0, k);
    return ans;
  }

  /**
   * 深度优先遍历获取节点值与其父节点的对应关系
   */
  private void findParents(TreeNode root) {
    if (root.left != null) {
      parents.put(root.left.val, root);
      findParents(root.left);
    }

    if (root.right != null) {
      parents.put(root.right.val, root);
      findParents(root.right);
    }
  }

  /**
   * 第 2 步：进行深度优先遍历
   */
  private void findAns(TreeNode node, TreeNode from, int depth, int k) {
    if (node == null) {
      return;
    }
    if (depth == k) {
      ans.add(node.val);
      return;
    }

    //向下向左寻找
    if (node.left != from) {
      findAns(node.left, node, depth + 1, k);
    }

    //向下向左寻找
    if (node.right != from) {
      findAns(node.right, node, depth + 1, k);
    }

    //向上寻找
    if (parents.get(node.val) != from) {
      findAns(parents.get(node.val), node, depth + 1, k);
    }
  }

  public static void main(String[] args) {
    AllNodesDistanceKInBT t = new AllNodesDistanceKInBT();
    TreeNode root = new TreeNode(3,
            new TreeNode(5,
                    new TreeNode(6),
                    new TreeNode(2,
                            new TreeNode(7),
                            new TreeNode(4))),
            new TreeNode(1,
                    new TreeNode(0),
                    new TreeNode(8))
    );
    System.out.println(t.distanceK(root, root.left, 2));
    TreeNode r2 = new TreeNode(1);
    System.out.println(t.distanceK(r2, r2, 3));
  }
}
