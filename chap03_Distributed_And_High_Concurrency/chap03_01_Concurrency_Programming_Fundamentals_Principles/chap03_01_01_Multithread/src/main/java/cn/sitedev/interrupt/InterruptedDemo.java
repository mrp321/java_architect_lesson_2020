package cn.sitedev.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptedDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("before:" + Thread.currentThread().isInterrupted());
                    // 对线程进行复位, 由true变为false
                    Thread.interrupted();
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        }, "InterruptedDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
