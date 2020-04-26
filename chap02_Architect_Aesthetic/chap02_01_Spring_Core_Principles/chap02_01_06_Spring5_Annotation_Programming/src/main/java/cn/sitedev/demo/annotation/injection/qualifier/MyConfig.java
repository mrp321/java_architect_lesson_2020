package cn.sitedev.demo.annotation.injection.qualifier;

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
        myDao.flag = "2";
        return myDao;

    }
}
