package cn.sitedev;

import lombok.Data;

@Data
public class ConcretePrototype implements IPrototype {
    private int age;
    private String name;

    @Override
    public ConcretePrototype clone() {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        concretePrototype.setAge(this.age);
        concretePrototype.setName(this.name);
        return concretePrototype;
    }
}
