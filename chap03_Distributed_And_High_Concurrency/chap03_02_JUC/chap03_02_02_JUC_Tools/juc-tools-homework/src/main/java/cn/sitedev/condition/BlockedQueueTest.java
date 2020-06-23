package cn.sitedev.condition;

/**
 * 自定义阻塞队列测试类
 */
public class BlockedQueueTest {
    public static void main(String[] args) {
        // 自定义阻塞队列, 容量为10
        BlockedQueue<Product> queue = new BlockedQueue<>(10);
        // 消费者
        Producer producer = new Producer(queue);
        // 生产者
        Consumer consumer = new Consumer(queue);
        // 生产者数量>=消费者数量
        for (int i = 0; i < 15; i++) {
            // 启动生产者线程
            new Thread(producer, "生產者" + i).start();
        }
        for (int i = 0; i < 5; i++) {
            // 启动消费者线程
            new Thread(consumer, "消費者" + i).start();
        }
    }
}
