package cn.sitedev.project.controller;

import cn.sitedev.project.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    @Autowired
    private MyService myService;

}
