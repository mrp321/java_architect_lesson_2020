package cn.sitedev.demo;

import cn.sitedev.demo.mapper.BlogMapper;
import cn.sitedev.mebatis.Configuration;
import cn.sitedev.mebatis.Executor;
import cn.sitedev.mebatis.SqlSession;

public class BlogMapperTest {
    public static void main(String[] args) {
        SqlSession sqlSession = new SqlSession(new Configuration(), new Executor());
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.selectById(1);
    }
}
