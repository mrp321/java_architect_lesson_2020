package cn.sitedev.chaining.shallow;

import java.util.ArrayList;
import java.util.Arrays;

public class PrototypeTest {
    public static void main(String[] args) {
        // 创建原型对象
        ConcretePrototype prototype = new ConcretePrototype();
        prototype.setAge(18);
        prototype.setName("Sitedev");
        prototype.setHobbies(new ArrayList<>(Arrays.asList("Sing", "Dance")));
        System.out.println("克隆前原型对象:" + prototype);

        // 拷贝原型对象
        ConcretePrototype clonetype = prototype.clone();
        clonetype.getHobbies().add("Swim");
        System.out.println("克隆后原型对象:" + prototype);
        System.out.println("克隆后克隆对象:" + clonetype);
    }
}
