package cn.sitedev.project.entity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class AirPlane {
    public AirPlane() {
        System.out.println("调用AirPlane的构造方法");
    }

    @PostConstruct
    public void addOil() {
        System.out.println("飞机起飞前加油");
    }

    public void run() {
        System.out.println("飞机在空中飞行");
    }

    @PreDestroy
    public void stop() {
        System.out.println("飞机停止飞行");
    }
}
