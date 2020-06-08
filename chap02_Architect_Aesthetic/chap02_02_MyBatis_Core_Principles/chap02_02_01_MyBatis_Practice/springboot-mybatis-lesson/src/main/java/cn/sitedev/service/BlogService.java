package cn.sitedev.service;

import cn.sitedev.entity.Blog;

public interface BlogService {
    Blog getBlogById(int bid);

    int addBlog(Blog blog);
}
