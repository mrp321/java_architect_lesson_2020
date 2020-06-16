package cn.sitedev.status;

import java.util.concurrent.TimeUnit;

public class ThreadStatus {
    public static void main(String[] args) {
        // TIME_WAITING
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "TIME_WAITING").start();

        // WAITING，线程在 ThreadStatus 类锁上通过 wait 进行等待
        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "WAITING").start();

        // 线程在 ThreadStatus 加锁后，不会释放锁
        new Thread(new BlockedDemo(), "BlockedDemo-01").start();
        new Thread(new BlockedDemo(), "BlockedDemo-02").start();
    }


    static class BlockedDemo extends Thread {
        @Override
        public void run() {
            synchronized (BlockedDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
