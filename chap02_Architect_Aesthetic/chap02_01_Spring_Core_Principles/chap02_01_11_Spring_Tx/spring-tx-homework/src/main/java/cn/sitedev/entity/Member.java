package cn.sitedev.entity;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String name;
    private String addr;
    private Integer age;

    public Member(String name, String addr, Integer age) {
        this.name = name;
        this.addr = addr;
        this.age = age;
    }
}
