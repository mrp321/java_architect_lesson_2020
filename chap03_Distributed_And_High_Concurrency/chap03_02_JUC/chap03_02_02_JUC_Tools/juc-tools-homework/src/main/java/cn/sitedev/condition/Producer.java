package cn.sitedev.condition;

import java.util.UUID;

/**
 * 生产者
 */
public class Producer implements Runnable {

    /**
     * 阻塞队列
     */
    private BlockedQueue<Product> queue;

    public Producer(BlockedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Product product = new Product(UUID.randomUUID().toString());
        System.out.println(Thread.currentThread().getName() + "=> 生产: " + product);
        queue.put(product);
    }
}
