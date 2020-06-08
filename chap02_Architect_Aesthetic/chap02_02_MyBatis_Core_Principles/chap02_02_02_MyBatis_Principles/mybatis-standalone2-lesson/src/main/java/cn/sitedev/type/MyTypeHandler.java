package cn.sitedev.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String parameter, JdbcType jdbcType) throws SQLException {
        // 设置String类型的参数的时候调用, java类型 到 JDBC类型
        // 注意只有在字段上添加typeHandler属性才会生效
        // insertBlog name字段
        System.out.println("--------------------setNonNullParameter1: " + parameter);
        preparedStatement.setString(i, parameter);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        // 根据列名获取 String 类型的参数的时候调用, JDBC 类型到java类型
        // 注意只有在字段上添加typeHandler 属性才会生效
        System.out.println("-------------------getNullableResult1: " + columnName);
        return resultSet.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        // 根据下标获取String类型参数的时候调用
        System.out.println("-----------------getNullableResult2: " + columnIndex);
        return resultSet.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        System.out.println("----------------getNullableResult3: " + columnIndex);
        return callableStatement.getString(columnIndex);
    }
}
