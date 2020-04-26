package cn.sitedev.demo.annotation.injection.value;

import cn.sitedev.project.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public Bird bird() {
        return new Bird();
    }
}
