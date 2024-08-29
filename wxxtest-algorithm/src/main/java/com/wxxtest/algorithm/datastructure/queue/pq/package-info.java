package com.wxxtest.algorithm.datastructure.queue.pq;
/*
优先队列(priority queue)
优先队列和堆的关系
优先队列（Priority Queue）是一种「抽象的」数据结构；而堆（Heap）是具体的实现。

解决的问题
「优先队列」是从这样的场景中抽象出来的数据结构：
班级里要选一名同学代表全班参加程序编程竞赛，此时我们只会关心第1名是谁。
如果第1名不想参赛了，或者说第1名因为其它因素不符合参考资格，我们才考虑第2名，
但也是从（除了第 1 名以外）剩下的那些同学中挑出第 1 名。
即问题场景：
我们 只关心当前「最优」的那个元素，第 2 名、第 3 名直到最后一名都不考虑了

「优先队列」相对于「普通队列」而言，「普通队列」的性质是「先进先出，后进后出」。
「优先队列」由元素的 优先级 决定出队的顺序。

实现优先队列的方式：
1、普通数组：数组尾部入队O(1),选择排序出队O(N)
2、顺序数组：插入排序入队O(N),最大出队O(1)
3、堆：入队O(logN)，出队O(logN)，这个复杂度我们猜测它大概率是一个树
实际上确实是一个二叉(树)堆

优先队列资料
1、https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/description/
2、https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
3、https://leetcode.cn/problems/sliding-window-median/description/
4、https://www.suanfa8.com/priority-queue

 */