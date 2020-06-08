package cn.sitedev.dbutils.dao;

import cn.sitedev.dbutils.HikariUtil;
import cn.sitedev.dbutils.dto.BlogDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BlogDao {
    private static QueryRunner queryRunner;

    static {
        queryRunner = HikariUtil.getQueryRunner();
    }

    // 返回单个对象, 通过new BeanHandler<>(Class<?> clazz)来设置封装
    public static BlogDto selectBlog(Integer bid) throws SQLException {
        String sql = "SELECT * FROM blog WHERE bid = ?";
        Object[] params = new Object[]{bid};
        BlogDto blogDto = queryRunner.query(sql, new BeanHandler<>(BlogDto.class), params);
        return blogDto;
    }

    // 返回列表, 通过new BeanListHandler<>(Class<?> clazz)来设置List的泛型
    public static void selectList() throws SQLException {
        String sql = "SELECT * FROM blog";
        List<BlogDto> list = queryRunner.query(sql, new BeanListHandler<>(BlogDto.class));
        list.forEach(System.out::println);
    }
}
