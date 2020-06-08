package cn.sitedev.mapper;

import cn.sitedev.entity.Blog;

public interface BlogMapper {
    Blog selectByPrimaryKey(Integer bid);

    int insert(Blog blog);
}
