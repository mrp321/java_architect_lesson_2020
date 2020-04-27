package cn.sitedev.demo.annotation.injection.primary;

import cn.sitedev.project.dao.MyDao2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MyConfig {
    @Bean
    public MyDao2 dao1() {
        MyDao2 dao = new MyDao2();
        dao.setFlag("2");
        return dao;
    }

    @Bean
    @Primary
    public MyDao2 dao2() {
        MyDao2 dao = new MyDao2();
        dao.setFlag("4");
        return dao;
    }


}
