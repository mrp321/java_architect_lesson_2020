package cn.sitedev;

public class PrototypeTest {
    public static void main(String[] args) {
        // 创建原型对象
        ConcretePrototype prototype = new ConcretePrototype();
        prototype.setAge(18);
        prototype.setName("Sitedev");
        System.out.println(prototype);

        // 拷贝原型对象
        ConcretePrototype clonetype = prototype.clone();
        System.out.println(prototype);
    }
}
