package cn.sitedev.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义类型处理器(java List<Integer>  => jdbc VARCHAR)
 */
public class MyTypeHandler extends BaseTypeHandler<List<Integer>> {

    /**
     * 给数据库指定字段赋值
     *
     * @param ps        PreparedStatement
     * @param i         参数索引
     * @param parameter java中参数的类型
     * @param jdbcType  jdbc类型
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        // java : List<Integer> => jdbc : VARCHAR
        // 例: [1,2,3] => 1,2,3
        ps.setString(i, Arrays.toString(parameter.toArray(new Integer[]{})).replaceAll("\\[|\\]", ""));
    }

    /**
     * 获取数据库指定字段值
     *
     * @param rs         ResultSet
     * @param columnName 字段名
     * @return
     * @throws SQLException
     */
    @Override
    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String[] columnValue2Arr = rs.getString(columnName).split(",");
        // jdbc : VARCHAR =>  java : List<Integer>
        return getPropertyValue(columnValue2Arr);
    }

    /**
     * 获取数据库指定字段值
     *
     * @param rs          ResultSet
     * @param columnIndex 字段索引
     * @return
     * @throws SQLException
     */
    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String[] columnValue2Arr = rs.getString(columnIndex).split(",");
        // jdbc : VARCHAR =>  java : List<Integer>
        return getPropertyValue(columnValue2Arr);
    }

    /**
     * 获取数据库指定字段值
     *
     * @param cs          CallableStatement
     * @param columnIndex 字段索引
     * @return
     * @throws SQLException
     */
    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String[] columnValue2Arr = cs.getString(columnIndex).split(",");
        // jdbc : VARCHAR =>  java : List<Integer>
        return getPropertyValue(columnValue2Arr);
    }

    private List<Integer> getPropertyValue(String[] columnValue2Arr) {
        List<Integer> propertyValue = new ArrayList<>(columnValue2Arr.length);
        Arrays.asList(columnValue2Arr).forEach(item -> propertyValue.add(Integer.valueOf(item.trim())));
        return propertyValue;
    }
}
