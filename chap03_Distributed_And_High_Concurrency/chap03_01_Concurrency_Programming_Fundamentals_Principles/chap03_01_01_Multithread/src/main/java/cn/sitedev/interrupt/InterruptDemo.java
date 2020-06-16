package cn.sitedev.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 默认情况下isinterrupted返回false
            // 通过thread.interrupt就变成了true
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("num :" + i);
        }, "interruptDemo");
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt(); // 加和不加的效果

    }
}
