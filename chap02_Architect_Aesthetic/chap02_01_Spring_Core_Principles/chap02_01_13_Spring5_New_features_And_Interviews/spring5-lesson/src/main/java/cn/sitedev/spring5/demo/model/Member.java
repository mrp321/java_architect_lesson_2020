package cn.sitedev.spring5.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String mid;
    private String name;
    private String addr;
    private String info;

    public Member(String name) {
        this.name = name;
    }
}
