package cn.sitedev.service;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public void get(long id) {
        System.out.println("根据id获取成员");
    }

    public void get() {
        System.out.println("获取成员");
    }

    public void save() {
        System.out.println("保存成员");
    }

    public void delete() {
        int i = 10 / 0;
        System.out.println("删除成员");
    }
}
