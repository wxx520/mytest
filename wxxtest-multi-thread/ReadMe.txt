「多线程安全问题」
【【原理】】

【背景】
为了提升性能，硬件在单核心提升到极限后，开始实现多核心/多线程。
在这种多核心环境下，cup和编译器为了提升代码性能，还设计了缓存和reorder的机制。
虽然这在提升性能的同时且保证了单核心下程序的语义正确，但是多核心却不能完全保证程序语义的正确性，也带来我们常说的多线程安全问题/并发问题，他包括两个方面
1.可见性问题：a线程修改了共享变量x，b线程确不可见，读的还是修改前的值
2.原子性问题

【原因】
多个线程共享变量，且至少有一个线程会修改它，即修改和共享同时存在。

【解决思路】
1、不共享
2、不修改
3、都无法避免，引入一套协调机制，去解决可见性原子性的问题。就是我们常听到的Java内存模型，它也是Java语言规范的一部分。

【理论基础：happens before 抽象层】
为了保证这种直觉的成立，比较直接的解决思路是，在导致这种直觉不成立的因素上找方案，即指定哪些位置不能进行指令重排，哪些地方不能直接使用缓存。
但是java语言规范作为一种规范，希望给各种实现一定的灵活性，并没有直接使用上述方式，按时引入一层抽象，也就是happens before 规则，有了这种规则，就能保证这种直觉的成立，主要包括三个方面：
1.单个线程内部：按照源码顺序存在happens before关系
2.多个线程之间，在线程之间相互影响的位置，选择性的指定一些happens before 规则：
    a.前一个线程对volatile变量的写happens before后一个线程对该volatile变量的读
    b.前一个线程退出synchronized块 happens before后一个线程进入synchronized 块
    c.主线程调用执行线程的start方法 happens before执行线程的run 方法
    d.....
3.happens before规则具有传递性，即if a happens before b，b happens before c，则有a happens before c
基于以上三个方面的happens-before规则，我们能严格的证明我们的程序是否并发问题。
举个例子，我们使用volatile变量实现piggyback，是实现非volatile变量跨线程读写的可见性
1.线程a，先修改非volatile变量，再修改volatile变量
2.线程b，先读取volatile变量，再读取非volatile变量
3.基于以上三条线程b能读到非volatile变量修改后的值

【Java语言实现】
基于以上理论，设计并实现一套机制，能让多个线程能够协调工作
1、锁
2、synchronized
3、volatile
4、wait/notify
5、Further 跨线程传参

