package cn.sitedev.prototype;

import java.util.Arrays;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcretePrototype prototype = new ConcretePrototype();
        prototype.setAge(18);
        prototype.setName("Sitedev");
        prototype.setList(Arrays.asList("test", 1.0D, 1L, 'A'));
        System.out.println("深拷贝前原型对象:" + prototype);

        ConcretePrototype clonetype = prototype.deepClone();
        System.out.println("深拷贝后原型对象:" + prototype);
        System.out.println("深拷贝后克隆对象:" + clonetype);
        System.out.println(prototype.getList() == clonetype.getList());

        clonetype = prototype.clone();
        System.out.println("浅拷贝前原型对象:" + prototype);
        System.out.println("浅拷贝后原型对象:" + prototype);
        System.out.println("浅拷贝后克隆对象:" + clonetype);
        System.out.println(prototype.getList() == clonetype.getList());
    }
}
