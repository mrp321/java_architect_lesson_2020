# 1. AQS中为什么采用双向链表，它和单向链表相比，有什么优势？

双向链表和单向链表相比有以下优势：
> 插入删除不需要移动元素外，可以原地插入删除
>
> 可以双向遍历


# 2. 请简述AQS的原理

获取同步状态

> 假设线程A要获取同步状态（这里想象成锁，方便理解），初始状态下state=0,所以线程A可以顺利获取锁，A获取锁后将state置为1。
>
> 在A没有释放锁期间，线程B也来获取锁，此时因为state=1，表示锁被占用，所以将B的线程信息和等待状态等信息构成出一个Node节点对象，放入同步队列，head和tail分别指向队列的头部和尾部（此时队列中有一个空的Node节点作为头点，这个新Node节点对象的head指向这个空节点，空Node的后继节点是B对应的Node节点，tail指向它），同时阻塞线程B(这里的阻塞使用的是LockSupport.park()方法)。
>
> 后续如果再有线程要获取锁，都会加入队列尾部并阻塞。

释放同步状态
> 当线程A释放锁时，即将state置为0，此时A会唤醒头节点的后继节点（所谓唤醒，其实是调用LockSupport.unpark(B)方法），即B线程从LockSupport.park()方法返回，此时B发现state已经为0，所以B线程可以顺利获取锁，B获取锁后B的Node节点随之出队。

