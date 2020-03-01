package cn.sitedev.prototype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConcretePrototype implements Cloneable {
    private String name;
    private int age;
    private List<Object> list = new ArrayList<>();

    /**
     * 浅拷贝
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected ConcretePrototype clone() throws CloneNotSupportedException {
        return (ConcretePrototype) super.clone();
    }

    /**
     * 深拷贝
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public ConcretePrototype deepClone() throws CloneNotSupportedException {
        ConcretePrototype prototype = (ConcretePrototype) super.clone();
        // JSON 序列化:Java对象=>byte[]
        byte[] jsonBytes = JSONObject.toJSONBytes(prototype);
        // JSON 反序列化:byte[]=>Java对象
        ConcretePrototype clonetype = JSON.parseObject(jsonBytes, ConcretePrototype.class);
        return clonetype;
    }
}