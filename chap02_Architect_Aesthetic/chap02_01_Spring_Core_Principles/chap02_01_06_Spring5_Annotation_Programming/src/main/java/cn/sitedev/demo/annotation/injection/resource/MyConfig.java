package cn.sitedev.demo.annotation.injection.resource;

import cn.sitedev.project.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"cn.sitedev.project.service", "cn.sitedev.project.dao"})
public class MyConfig {

    @Bean
    public MyDao dao() {
        MyDao myDao = new MyDao();
        myDao.flag = "4";
        return myDao;

    }

    @Bean
    public MyDao dao2() {
        MyDao myDao = new MyDao();
        myDao.flag = "2";
        return myDao;
    }
}
