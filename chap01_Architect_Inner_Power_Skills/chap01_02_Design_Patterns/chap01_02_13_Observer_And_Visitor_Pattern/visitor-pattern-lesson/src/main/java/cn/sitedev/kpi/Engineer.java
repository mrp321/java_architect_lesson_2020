package cn.sitedev.kpi;

import java.util.Random;

// 工程师
public class Engineer extends Employee {
    public Engineer(String name) {
        super(name);
    }

    @Override
    public void accept(IVisitor vistor) {
        vistor.visit(this);
    }

    // 工程师一年的代码量
    public int getCodeLines() {
        return new Random().nextInt(100000);
    }
}
