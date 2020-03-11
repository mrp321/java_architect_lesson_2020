package cn.sitedev.pool;

import java.sql.Connection;

public class ConnectionPoolTest {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool();
        Connection conn = pool.getConnection();
        System.out.println(conn);
    }
}
