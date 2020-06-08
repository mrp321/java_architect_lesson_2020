package cn.sitedev.demo.mapper;

import cn.sitedev.demo.entity.Blog;

public interface BlogMapper {
    Blog selectById(Integer bid);
}
