package cn.sitedev;

public class MyRunnable extends OtherClass implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable.run()");
    }

    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}

class OtherClass {

}