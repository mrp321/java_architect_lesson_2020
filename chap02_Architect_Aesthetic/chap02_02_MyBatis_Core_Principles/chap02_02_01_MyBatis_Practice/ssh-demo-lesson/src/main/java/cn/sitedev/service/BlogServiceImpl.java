package cn.sitedev.service;

import cn.sitedev.dao.BlogDao;
import cn.sitedev.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Blog> queryBlog()
    {
        return blogDao.findAll();
    }

    @Override
    public void addBlog(Blog blog)
    {
        blogDao.save(blog);
    }
}
