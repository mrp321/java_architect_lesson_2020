package cn.sitedev.threadlocal;

public class ExecutorThread implements Runnable {
    @Override
    public void run() {
        System.out.println(ThreadLocalSingleton.getInstance());
    }
}
