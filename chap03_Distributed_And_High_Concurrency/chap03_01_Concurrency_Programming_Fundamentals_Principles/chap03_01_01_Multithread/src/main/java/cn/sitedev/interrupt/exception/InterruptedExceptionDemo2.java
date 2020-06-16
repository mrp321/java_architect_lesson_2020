package cn.sitedev.interrupt.exception;

import java.util.concurrent.TimeUnit;

public class InterruptedExceptionDemo2 {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Num : " + i);
        }, "InterruptedExceptionDemo2");

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();

        System.out.println(thread.isInterrupted());
    }
}
