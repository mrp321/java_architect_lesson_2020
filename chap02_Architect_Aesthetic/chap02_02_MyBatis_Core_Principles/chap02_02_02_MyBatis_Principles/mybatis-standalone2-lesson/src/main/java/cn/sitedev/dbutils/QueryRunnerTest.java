package cn.sitedev.dbutils;

import cn.sitedev.dbutils.dao.BlogDao;

import java.sql.SQLException;

public class QueryRunnerTest {
    public static void main(String[] args) throws SQLException {
        HikariUtil.init();
        System.out.println(BlogDao.selectBlog(1));
        // Language Level 设置成Java 8
        BlogDao.selectList();
    }
}
