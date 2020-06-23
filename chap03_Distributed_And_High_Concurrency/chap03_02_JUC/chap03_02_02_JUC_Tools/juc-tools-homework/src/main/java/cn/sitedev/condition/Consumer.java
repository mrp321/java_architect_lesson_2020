package cn.sitedev.condition;

/**
 * 消费者
 */
public class Consumer implements Runnable {

    /**
     * 阻塞队列
     */
    private BlockedQueue<Product> queue;

    public Consumer(BlockedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Product product = queue.take();
        System.out.println(Thread.currentThread().getName() + "=> 消费: " + product);
    }
}