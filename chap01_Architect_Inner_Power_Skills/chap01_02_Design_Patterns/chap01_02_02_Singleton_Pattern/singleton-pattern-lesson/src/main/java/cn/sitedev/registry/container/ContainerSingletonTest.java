package cn.sitedev.registry.container;

import cn.sitedev.lazy.ExecutorThread;

public class ContainerSingletonTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ExecutorThread());
        Thread t2 = new Thread(new ExecutorThread());
        t1.start();
        t2.start();
    }
}
