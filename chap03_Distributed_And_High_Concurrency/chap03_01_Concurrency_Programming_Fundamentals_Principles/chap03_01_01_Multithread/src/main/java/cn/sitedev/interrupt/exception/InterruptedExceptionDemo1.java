package cn.sitedev.interrupt.exception;

import java.util.concurrent.TimeUnit;

public class InterruptedExceptionDemo1 {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Num : " + i);
        }, "InterruptedExceptionDemo1");

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();

        System.out.println(thread.isInterrupted());
    }
}
