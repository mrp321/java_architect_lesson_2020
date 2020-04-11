package cn.sitedev.kpi;

import java.util.Random;

/**
 * 员工基类
 */
public abstract class Employee {
    protected String name;
    // 员工kpi
    protected int kpi;

    public Employee(String name) {
        this.name = name;
        this.kpi = new Random().nextInt(10);
    }

    // 核心方法, 接受访问者的访问
    public abstract void accept(IVisitor vistor);
}
