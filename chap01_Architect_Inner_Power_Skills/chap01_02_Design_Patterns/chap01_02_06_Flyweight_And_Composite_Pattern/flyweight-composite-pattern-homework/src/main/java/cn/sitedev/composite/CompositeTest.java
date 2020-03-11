package cn.sitedev.composite;

public class CompositeTest {
    public static void main(String[] args) {
        System.out.println("===========透明组合模式==============");

        Node javaBase = new Leaf("Java 入门课程");
        Node ai = new Leaf("人工智能");

        Node packageCourse = new Branch("Java 架构师课程");
        Node design = new Leaf("Java 设计模式");
        Node source = new Leaf("源码分析");
        Node softSkill = new Leaf("软技能");

        packageCourse.addChild(design);
        packageCourse.addChild(source);
        packageCourse.addChild(softSkill);

        Node catalog = new Branch("课程主目录");
        catalog.addChild(javaBase);
        catalog.addChild(ai);
        catalog.addChild(packageCourse);

        catalog.show();
        packageCourse.show();
        softSkill.show();
    }
}
