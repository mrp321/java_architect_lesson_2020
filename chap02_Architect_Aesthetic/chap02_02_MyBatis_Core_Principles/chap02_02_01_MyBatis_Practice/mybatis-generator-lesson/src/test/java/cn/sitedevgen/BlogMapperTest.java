package cn.sitedevgen;

import cn.sitedevgen.dao.BlogMapper;
import cn.sitedevgen.entity.Blog;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class BlogMapperTest {
    public static void main(String[] args) {
        InputStream inputStream = BlogMapperTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectByPrimaryKey(1);
        System.out.println(blog);
    }
}
