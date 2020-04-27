package cn.sitedev.demo.annotation.configure.lifecycle;

import cn.sitedev.project.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({@ComponentScan("cn.sitedev.project.entity"), @ComponentScan("cn.sitedev.demo.annotation.configure.lifecycle")})
public class MyConfig {

    /**
     * 对Bean生命周期的控制
     * 1. 配置@Bean的参数
     * 2. 分别实现InitializingBean和DisposableBean接口
     * 3. 使用@PostConstruct和@PreDistroy注解
     * 4. 自己编写一个类, 实现BeanPostProcessor接口
     *
     * @return
     */
    @Bean(initMethod = "addOil", destroyMethod = "stop")
    public Car car() {
        return new Car();
    }
}
