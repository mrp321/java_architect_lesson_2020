package cn.sitedev.project.service;

import cn.sitedev.project.dao.MyDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService2 {
    @Autowired
    private MyDao2 myDao2;

    public void printMyDao2() {
        System.out.println(myDao2);
    }
}
