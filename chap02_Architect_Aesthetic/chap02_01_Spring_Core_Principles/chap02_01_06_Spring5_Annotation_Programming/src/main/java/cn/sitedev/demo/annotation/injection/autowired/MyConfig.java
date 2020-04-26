package cn.sitedev.demo.annotation.injection.autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"cn.sitedev.project.controller", "cn.sitedev.project.service", "cn.sitedev.project.dao"})
public class MyConfig {
}
