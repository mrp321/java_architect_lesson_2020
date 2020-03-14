package cn.sitedev.general;

public class Test {
    public static void main(String[] args) {
        // 来一个实现化角色
        IImplementor iImplementor = new ConcreteImplementorA();
        // 来一个抽象化角色, 聚合实现
        Abstraction abstraction = new RefinedAbstraction(iImplementor);
        // 执行操作
        abstraction.operation();
    }
}
