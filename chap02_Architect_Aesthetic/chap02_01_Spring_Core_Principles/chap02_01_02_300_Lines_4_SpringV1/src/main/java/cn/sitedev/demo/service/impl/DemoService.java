package cn.sitedev.demo.service.impl;

import cn.sitedev.demo.service.IDemoService;
import cn.sitedev.mvcframework.annotation.MyService;

/**
 * 核心业务逻辑
 */
@MyService
public class DemoService implements IDemoService {
    @Override
    public String get(String name) {
        return "My name is " + name;
    }
}
