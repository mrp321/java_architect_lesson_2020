package cn.sitedev.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class HighConcurrencyDemo extends Thread {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new HighConcurrencyDemo().start();
        }
        countDownLatch.countDown();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadName: " + Thread.currentThread().getName());
    }
}
