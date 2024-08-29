package com.wxxtest.algorithm.datastructure.list;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

  /**
   * 建立新老节点的映射关系，已知老random，根据映射关系可以获得新random
   * 再关联到新节点中即可
   */
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }
    Map<Node, Node> map = new HashMap<>();
    Node cur = head;
    while (cur != null) {
      map.put(cur, new Node(cur.val));
      cur = cur.next;
    }

    cur = head;
    while (cur != null) {
      Node node = map.get(cur);
      node.next = map.get(cur.next);
      node.random = map.get(cur.random);
      cur = cur.next;
    }
    return map.get(head);
  }

  public static void main(String[] args) {
    CopyRandomList c = new CopyRandomList();
    Node head = new Node(7);
    head.next = new Node(13);
    head.next.next = new Node(11);
    head.next.next.next = new Node(10);
    head.next.next.next.next = new Node(1);
    head.next.random = head;
    head.next.next.random = head.next.next.next.next;
    head.next.next.next.random = head.next.next;
    head.next.next.next.next.random = head;
    System.out.println(c.copyRandomList(head));
  }
}
