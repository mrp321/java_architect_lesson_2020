package cn.sitedev.demo.annotation.injection.propertysource;

import cn.sitedev.project.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:value.properties")
public class MyConfig {
    @Bean
    public Bird bird() {
        return new Bird();
    }
}
