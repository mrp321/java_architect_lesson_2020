# 1. 简述线程池的好处
* 降低创建线程和销毁线程的性能开销
* 提高响应速度，当有新任务需要执行是不需要等待线程创建就可以立马执行
* 合理的设置线程池大小可以避免因为线程数超过硬件资源瓶颈带来的问题

# 2. Java中提供了哪几种线程的实现，分别适合应用在什么场景？

* newFixedThreadPool：该方法返回一个固定数量的线程池，线程数不变，当有一个任务提交
时，若线程池中空闲，则立即执行，若没有，则会被暂缓在一个任务队列中，等待有空闲的线程
去执行。
* newSingleThreadExecutor : 创建一个线程的线程池，若空闲则执行，若没有空闲线程则暂缓在
任务队列中。
* newCachedThreadPool： 返回一个可根据实际情况调整线程个数的线程池，不限制最大线程数
量，若用空闲的线程则执行任务，若无任务则不创建线程。并且每一个空闲线程会在 60 秒后自
动回收
* newScheduledThreadPool: 创建一个可以指定线程的数量的线程池，但是这个线程池还带有延
迟和周期性执行任务的功能，类似定时器。

# 3. 线程池的核心参数有哪些，分别表示什么意思?


```java
// corePoolSize: 核 心 线 程 数
// maximumPoolSize: 最 大 线 程 数
// keepAliveTime: 超 时 时 间 ,超 出 核 心 线 程 数 量 以 外 的 线 程 空 余 存 活 时 间
// unit: 存 活 时 间 单 位
// workQueue: 保 存 执 行 任 务 的 队 列
// threadFactory: 创 建 新 线 程 使 用 的 工 厂
// handler: 当 任 务 无 法 执 行 的 时 候 的 处 理 方 法
public ThreadPoolExecutor(int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    ThreadFactory threadFactory,
    RejectedExecutionHandler handler) {
}
```

