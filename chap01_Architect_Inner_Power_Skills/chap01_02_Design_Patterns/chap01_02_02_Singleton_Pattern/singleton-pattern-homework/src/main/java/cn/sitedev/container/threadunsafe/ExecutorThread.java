package cn.sitedev.container.threadunsafe;

public class ExecutorThread implements Runnable {
    @Override
    public void run() {
        System.out.println(ContainerSingleton.getBean(Object.class.getName()));
    }
}
