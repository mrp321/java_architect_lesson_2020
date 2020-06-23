package cn.sitedev.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicDemo {
    private static int count = 0;

    private static Lock lock = new ReentrantLock();

    public static void inc() {
        lock.lock();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> AtomicDemo.inc()).start();
        }
        Thread.sleep(3000);
        System.out.println("result : " + count);
    }
}
