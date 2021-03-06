package cn.sitedev.mybatis.v1;

import cn.sitedev.mybatis.v1.mapper.BlogMapper;

public class MyBatisBoot {
    public static void main(String[] args) {
        MySqlSession sqlSession = new MySqlSession(new MyConfiguration(), new MyExecutor());
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.selectBlogById(1);
    }
}
