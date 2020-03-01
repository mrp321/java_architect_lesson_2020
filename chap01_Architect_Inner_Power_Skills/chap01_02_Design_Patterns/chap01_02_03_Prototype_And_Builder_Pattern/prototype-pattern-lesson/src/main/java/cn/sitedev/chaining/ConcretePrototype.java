package cn.sitedev.chaining;

import lombok.Data;

@Data
public class ConcretePrototype implements Cloneable {
    private int age;
    private String name;

    @Override
    protected ConcretePrototype clone() throws CloneNotSupportedException {
        try {
            return (ConcretePrototype) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
