package cn.sitedev.orm.test;

import cn.sitedev.orm.demo.entity.Member;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class JdbcTest {

    public static void main(String[] args) {
        //ORM，完成了一部分，只完成了从 数据表到对象的映射
        //对象到数据库表还没有
        //我传的条件是一条SQL语句，我还是在面向SQL编程
//        List<Member> result = select("select * from t_member");

        //这就是OO编程，ORM
        Member condition = new Member();
        condition.setName("TomCat");
        condition.setAge(2);

        //"select * from t_member where name = 'Tom' and age = 19"
        List<?> result = select(condition);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public static List<?> select(Object condition) {
        List<Object> result = new ArrayList<>();

        Class<?> entityClass = condition.getClass();


        Connection con = null;          //连接对象
        PreparedStatement pstm = null;  //语句集
        ResultSet rs = null;            //结果集


        try {
            //1、加载驱动类，千万不要忘记了
            Class.forName("com.mysql.jdbc.Driver");
            //2、建立连接
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spring-db-demo", "root", "root");

            Map<String, String> getFieldNameByColumn = new HashMap<String, String>();
            Map<String, String> getColumnByFieldName = new HashMap<String, String>();
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    String columnName = column.name();
                    getFieldNameByColumn.put(columnName, fieldName);
                    getColumnByFieldName.put(fieldName, columnName);
                } else {
                    //默认属性名就是列名
                    getFieldNameByColumn.put(fieldName, fieldName);
                    getColumnByFieldName.put(fieldName, fieldName);
                }
            }


            StringBuffer sql = new StringBuffer();
            //3、创建语句集
            Table table = entityClass.getAnnotation(Table.class);
            sql.append("select * from " + table.name() + " where 1=1 ");
            for (Field field : fields) {

                Object value = field.get(condition);
                if (null != value) {
                    if (String.class == field.getType()) {
                        sql.append(" and " + getColumnByFieldName.get(field.getName()) + " = '" + value + "'");
                    } else {
                        sql.append(" and " + getColumnByFieldName.get(field.getName()) + " = " + value);
                    }
                    //其他依次类推
                }
            }

            pstm = con.prepareStatement(sql.toString());

            //4、执行，获取结果集
            rs = pstm.executeQuery();

            int columnCounts = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object instance = entityClass.newInstance();
                for (int i = 1; i <= columnCounts; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    Field field = entityClass.getDeclaredField(getFieldNameByColumn.get(columnName));
                    field.setAccessible(true);
                    field.set(instance, rs.getObject(columnName));
                }
                result.add(instance);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        //6、关闭结果集、关闭语句集、关闭连接
        finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

//    private static List<Member> select(String sql) {
//        List<Member> result = new ArrayList<>();
//        Connection con = null;          //连接对象
//        PreparedStatement pstm = null;  //语句集
//        ResultSet rs = null;            //结果集
//        try {
//            //1、加载驱动类，千万不要忘记了
//            Class.forName("com.mysql.jdbc.Driver");
//            //2、建立连接
//            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spring-db-demo", "root", "root");
//            //3、创建语句集
//            pstm = con.prepareStatement(sql);
//            //4、执行语句集
//            rs = pstm.executeQuery();
//            while (rs.next()) {
//                //纯粹的硬编码
//                Member instance = new Member();
//                instance.setId(rs.getLong("id"));
//                instance.setName(rs.getString("name"));
//                instance.setAge(rs.getInt("age"));
//                instance.setAddr(rs.getString("addr"));
//                result.add(instance);
//            }
//            //5、获取结果集
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //6、关闭结果集、关闭语句集、关闭连接
//        finally {
//            try {
//                rs.close();
//                pstm.close();
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }


    private static List<Member> select(String sql) {
        List<Member> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1、加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //2、建立连接
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spring-db-demo", "root", "root");
            //3、创建语句集
            pstm = con.prepareStatement(sql);
            //4、执行语句集
            rs = pstm.executeQuery();
            while (rs.next()) {
                Member instance = mapperRow(rs, rs.getRow());
                result.add(instance);
            }
            //5、获取结果集
        } catch (Exception e) {
            e.printStackTrace();
        }
        //6、关闭结果集、关闭语句集、关闭连接
        finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static Member mapperRow(ResultSet rs, int i) throws Exception {
        Member instance = new Member();
        instance.setId(rs.getLong("id"));
        instance.setName(rs.getString("name"));
        instance.setAge(rs.getInt("age"));
        instance.setAddr(rs.getString("addr"));
        return instance;
    }

}
