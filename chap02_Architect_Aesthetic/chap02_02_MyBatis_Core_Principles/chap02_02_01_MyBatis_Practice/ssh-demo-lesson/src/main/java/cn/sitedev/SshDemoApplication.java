package cn.sitedev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SshDemoApplication {
    // 注意是mybatis数据库
    public static void main(String[] args) {
        SpringApplication.run(SshDemoApplication.class, args);
    }

}
