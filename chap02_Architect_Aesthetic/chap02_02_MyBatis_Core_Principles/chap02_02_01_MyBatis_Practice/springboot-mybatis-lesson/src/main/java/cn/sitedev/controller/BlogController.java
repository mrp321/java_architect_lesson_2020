package cn.sitedev.controller;

import cn.sitedev.entity.Blog;
import cn.sitedev.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/list")
    public Blog getBlog(Integer bid) {
        return this.blogService.getBlogById(bid);
    }
}
