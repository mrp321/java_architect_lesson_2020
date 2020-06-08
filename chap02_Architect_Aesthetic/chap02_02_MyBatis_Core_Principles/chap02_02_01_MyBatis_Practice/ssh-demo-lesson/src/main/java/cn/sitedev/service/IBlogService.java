package cn.sitedev.service;

import cn.sitedev.domain.Blog;
import java.util.List;

public interface IBlogService {
    public List<Blog> queryBlog();

    public void addBlog(Blog blog);
}
