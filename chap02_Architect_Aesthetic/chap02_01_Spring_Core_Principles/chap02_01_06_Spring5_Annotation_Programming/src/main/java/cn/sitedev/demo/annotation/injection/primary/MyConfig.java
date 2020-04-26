package cn.sitedev.demo.annotation.injection.primary;

import cn.sitedev.project.dao.MyDao2;
import cn.sitedev.project.dao.MyDao21;
import cn.sitedev.project.dao.MyDao22;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
//@ComponentScan({"cn.sitedev.project.service", "cn.sitedev.project.dao"})
public class MyConfig {
    @Bean("myDao2")
    public MyDao2 dao2() {
        MyDao21 dao = new MyDao21();
        dao.flag = "2";
        return dao;
    }

    @Bean("myDao2")
    @Primary
    public MyDao2 dao() {
        MyDao22 dao = new MyDao22();
        dao.flag = "4";
        return dao;
    }


}
