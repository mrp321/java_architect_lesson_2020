package cn.sitedev.project.service;

import cn.sitedev.project.dao.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyService {
    // 优先级: Resource > Qualifier > Autowired
    @Resource(name = "dao2")
    @Qualifier("dao")
    @Autowired
    private MyDao myDao;

    public void printMyDao() {
        System.out.println(myDao);
    }

}
