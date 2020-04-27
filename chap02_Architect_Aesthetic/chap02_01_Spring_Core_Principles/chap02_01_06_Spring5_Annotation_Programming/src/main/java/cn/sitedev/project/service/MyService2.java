package cn.sitedev.project.service;

import cn.sitedev.project.dao.MyDao2;

//@Service
public class MyService2 {
//    @Qualifier("myDao2")
//    @Autowired
    private MyDao2 myDao2;

    public void printMyDao2() {
        System.out.println(myDao2);
    }
}
