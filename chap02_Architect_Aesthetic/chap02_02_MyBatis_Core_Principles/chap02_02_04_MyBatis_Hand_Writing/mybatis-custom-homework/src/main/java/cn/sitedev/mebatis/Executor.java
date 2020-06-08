package cn.sitedev.mebatis;

import cn.sitedev.demo.entity.Blog;

import java.sql.*;
import java.util.ResourceBundle;

public class Executor {
    private static final ResourceBundle jdbcConfig = ResourceBundle.getBundle("jdbc");

    public <T> T query(String sql, Object parameter) {
         Connection connection = null;
         Statement statement = null;
         ResultSet resultSet = null;
        Blog blog = null;
        try {
            // 注册JDBC驱动
            Class.forName(jdbcConfig.getString("jdbc.driver"));
            // 建立连接
            connection = DriverManager.getConnection(jdbcConfig.getString("jdbc.url"), jdbcConfig.getString("jdbc" +
                    ".username"), jdbcConfig.getString("jdbc.password"));
            // 执行查询
            statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format(sql, parameter));
            // 获取结果集
            while (resultSet.next()) {
                Integer bid = resultSet.getInt("bid");
                String name = resultSet.getString("name");
                Integer authorId = resultSet.getInt("author_id");
                blog = new Blog(bid, name, authorId);
            }
            System.out.println(blog);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return (T) blog;
    }
}
