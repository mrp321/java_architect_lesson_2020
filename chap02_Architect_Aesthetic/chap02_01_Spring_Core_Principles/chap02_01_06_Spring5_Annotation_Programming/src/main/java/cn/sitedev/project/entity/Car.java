package cn.sitedev.project.entity;

public class Car {
    public Car() {
        System.out.println("调用Car的构造方法");
    }

    public void addOil() {
        System.out.println("加油");
    }

    public void run() {
        System.out.println("行驶");
    }

    public void stop() {
        System.out.println("停止");
    }
}
