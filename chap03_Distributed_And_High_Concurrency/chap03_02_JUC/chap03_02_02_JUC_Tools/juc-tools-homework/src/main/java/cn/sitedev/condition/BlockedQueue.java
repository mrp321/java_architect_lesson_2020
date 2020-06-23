package cn.sitedev.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定義阻塞隊列
 *
 * @param <T> 元素類型
 */
public class BlockedQueue<T> {

    /**
     * 存儲元素
     */
    private List<T> data;

    /**
     * 隊列容量
     */
    private int capacity;

    /**
     * 鎖
     */
    private Lock lock = new ReentrantLock();
    /**
     * 生產者條件:隊列不滿就生產
     */
    private Condition notFull = lock.newCondition();
    /**
     * 消費者條件:隊列不空就消費
     */
    private Condition notEmpty = lock.newCondition();

    public BlockedQueue(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>(capacity);
    }

    /**
     * 队列尾加入元素
     *
     * @param ele 元素
     */
    public void put(T ele) {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }
            this.data.add(ele);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 队列头移除元素
     *
     * @return
     */
    public T take() {
        T element = null;
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            element = this.data.remove(0);
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return element;
    }

    /**
     * 隊列是否為空
     *
     * @return
     */
    public boolean isEmpty() {
        lock.lock();
        try {
            return data.size() == 0;
        } finally {
            lock.unlock();
        }

    }

    /**
     * 隊列是否已滿
     *
     * @return
     */
    public boolean isFull() {
        lock.lock();
        try {
            return data.size() == this.capacity;
        } finally {
            lock.unlock();
        }
    }
}
