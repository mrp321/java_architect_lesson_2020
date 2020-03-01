package cn.sitedev.lazy.doublecheck;

public class ExecutorThread implements Runnable {
    @Override
    public void run() {
        LazyDoubleCheckSingleton singleton = LazyDoubleCheckSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + singleton);
    }
}