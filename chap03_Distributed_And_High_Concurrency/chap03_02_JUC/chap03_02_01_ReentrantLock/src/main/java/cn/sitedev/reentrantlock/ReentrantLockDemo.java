package cn.sitedev.reentrantlock;

public class ReentrantLockDemo {
    public synchronized void demo() {
        System.out.println("begin:demo");
        demo2();
    }

    public void demo2() {
        System.out.println("begin:demo2");
        synchronized (this) {

        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        new Thread(demo::demo).start();
    }
}
