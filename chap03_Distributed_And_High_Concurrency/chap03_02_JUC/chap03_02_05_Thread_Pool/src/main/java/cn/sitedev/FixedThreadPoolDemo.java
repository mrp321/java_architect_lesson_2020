package cn.sitedev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo implements Runnable {
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            executorService.execute(new FixedThreadPoolDemo());
        }
        executorService.shutdown();
    }
}
