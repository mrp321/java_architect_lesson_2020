package cn.sitedev;

import java.util.concurrent.ExecutorService;

public class ThreadPoolMonitorDemoTest implements Runnable {

    private static ExecutorService executorService = ThreadPoolMonitorDemo.newCachedThreadPool();

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ThreadPoolMonitorDemoTest());
        }
        executorService.shutdown();
    }
}
