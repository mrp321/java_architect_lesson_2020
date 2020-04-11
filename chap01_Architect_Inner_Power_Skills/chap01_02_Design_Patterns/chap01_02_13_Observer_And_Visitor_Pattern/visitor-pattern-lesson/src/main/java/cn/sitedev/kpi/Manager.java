package cn.sitedev.kpi;

import java.util.Random;

// 经理
public class Manager extends Employee {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void accept(IVisitor vistor) {
        vistor.visit(this);
    }

    // 一年做的产品数量
    public int getProducts() {
        return new Random().nextInt(10);
    }
}
