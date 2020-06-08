package cn.sitedev.service.impl;

import cn.sitedev.entity.Blog;
import cn.sitedev.mapper.BlogMapper;
import cn.sitedev.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlogById(int bid) {
        return this.blogMapper.selectByPrimaryKey(bid);
    }

    @Override
    public int addBlog(Blog blog) {
        return this.blogMapper.insert(blog);
    }
}
