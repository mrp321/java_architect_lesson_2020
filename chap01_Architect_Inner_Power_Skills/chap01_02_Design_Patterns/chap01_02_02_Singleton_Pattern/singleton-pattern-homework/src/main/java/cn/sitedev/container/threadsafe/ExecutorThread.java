package cn.sitedev.container.threadsafe;

public class ExecutorThread implements Runnable {
    @Override
    public void run() {
        System.out.println(ContainerSingleton.getBean(Object.class.getName()));
    }
}
