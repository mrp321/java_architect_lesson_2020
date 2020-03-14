package cn.sitedev.jdbc.framework;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public final List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) {
        try {
            // 1. 获取连接
            Connection connection = this.getConnection();
            // 2. 创建语句集
            PreparedStatement preparedStatement = this.createPrepareStatement(connection, sql);
            // 3. 执行语句集
            ResultSet resultSet = this.executeQuery(preparedStatement, values);
            // 4. 处理结果集
            List<?> result = this.parseResultSet(resultSet, rowMapper);
            // 5. 关闭结果集
            resultSet.close();
            // 6. 关闭语句集
            preparedStatement.close();
            // 7. 关闭连接
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    protected PreparedStatement createPrepareStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    protected ResultSet executeQuery(PreparedStatement preparedStatement, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i, values[i]);
        }
        return preparedStatement.executeQuery();
    }

    protected List<?> parseResultSet(ResultSet resultSet, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNum = 0;
        while (resultSet.next()) {
            result.add(rowMapper.mapRow(resultSet, rowNum++));
        }
        return result;
    }
}
