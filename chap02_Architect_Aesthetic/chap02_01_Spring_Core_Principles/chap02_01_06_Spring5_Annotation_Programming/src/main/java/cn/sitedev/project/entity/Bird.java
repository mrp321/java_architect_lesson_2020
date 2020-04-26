package cn.sitedev.project.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Bird {
    /**
     * 支持的类型:
     * 1. 基本数据类型
     * 2. 支持Spring EL表达式
     */
    @Value("鹦鹉")
    private String name;

    @Value("#{8-5}")
    private int age;

    @Value("${bird.color}")
    private String color;
}
