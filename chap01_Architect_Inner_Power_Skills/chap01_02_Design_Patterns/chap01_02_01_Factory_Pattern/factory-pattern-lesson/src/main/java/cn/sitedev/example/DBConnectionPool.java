package cn.sitedev.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * 数据库连接池管理类
 */
public class DBConnectionPool extends Pool {
    /**
     * 正在使用的连接数
     */
    private int checkedOut;

    /**
     * 存放产生的连接对象容器
     */
    private Vector<Connection> freeConnections = new Vector<>();

    /**
     * 密码
     */
    private String password = null;

    /**
     * 连接字符串
     */
    private String url = null;

    /**
     * 用户名
     */
    private String userName = null;

    /**
     * 空闲连接数
     */
    private static int num = 0;

    /**
     * 当前可用的连接数
     */
    private static int numActive = 0;

    /**
     * 连接池实例变量
     */
    private static DBConnectionPool pool = null;

    /**
     * 产生数据连接池
     *
     * @return
     */
    public static synchronized DBConnectionPool getInstance() {
        if (pool == null) {
            pool = new DBConnectionPool();
        }
        return pool;
    }

    /**
     * 获得一个数据连接池的实例
     */
    private DBConnectionPool() {
        try {
            init();
            // 初始化normalConnect个连接
            Connection connection = null;
            for (int i = 0; i < this.normalConnect; i++) {
                connection = this.newConnection();
                if (connection != null) {
                    // 往容器中添加一个连接对象
                    this.freeConnections.addElement(connection);
                    // 记录总连接数
                    num++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化
     */
    private void init() throws IOException {
        InputStream inputStream = DBConnectionPool.class.getResourceAsStream(propertiesName);
        Properties properties = new Properties();
        properties.load(inputStream);
        this.userName = properties.getProperty("userName");
        this.password = properties.getProperty("password");
        this.driverName = properties.getProperty("driverName");
        this.url = properties.getProperty("url");
        this.maxConnect = Integer.parseInt(properties.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(properties.getProperty("normalConnect"));
    }

    /**
     * 如果不再使用某个连接对象时, 可调用此方法将该对象释放到连接池
     *
     * @param connection 连接对象
     */
    @Override
    public synchronized void freeConnection(Connection connection) {
        this.freeConnections.add(connection);
        num++;
        this.checkedOut++;
        numActive++;
        // 解锁
        notifyAll();
    }

    /**
     * 创建一个新连接
     *
     * @return
     */
    private Connection newConnection() {
        Connection connection = null;
        try {
            if (this.userName == null || this.password == null) {
                // 用户名/密码为空
                connection = DriverManager.getConnection(this.url);
            } else {
                connection = DriverManager.getConnection(this.url, this.userName, this.password);
            }
            System.out.println("连接池创建一个新的连接");
        } catch (SQLException e) {
            System.out.println("无法创建这个URL的连接:" + this.url);
        }
        return connection;
    }

    /**
     * 返回当前空闲连接数
     *
     * @return
     */
    @Override
    public int getnum() {
        return num;
    }

    /**
     * 返回当前连接数
     *
     * @return
     */
    @Override
    public int getnumActive() {
        return numActive;
    }

    /**
     * (单例模式)获取一个可用连接
     *
     * @return
     */
    @Override
    public synchronized Connection getConnection() {
        Connection connection = null;
        // 还有空闲的连接
        if (this.freeConnections.size() > 0) {
            num++;
            connection = this.freeConnections.firstElement();
            this.freeConnections.removeElementAt(0);
            try {
                if (connection.isClosed()) {
                    System.out.println("从连接池删除一个无效连接");
                    connection = getConnection();
                }
            } catch (SQLException e) {
                System.out.println("从连接池删除一个无效连接");
                connection = getConnection();
            }
        } else if (this.maxConnect == 0 || this.checkedOut < this.maxConnect) {
            // 没有空闲连接且当前连接小于最大允许值, 最大值为0则不限制
            connection = this.newConnection();
        }
        if (connection != null) {
            // 当前连接数+1
            this.checkedOut++;
        }
        numActive++;
        return connection;
    }

    /**
     * 获取一个连接, 并加上等待时间限制, 时间单位为ms
     *
     * @param timeout 设置该连接的持续时间(以ms为单位)
     * @return
     */
    @Override
    public synchronized Connection getConnection(long timeout) {
        long startTime = System.currentTimeMillis();
        Connection connection = null;
        while ((connection = this.getConnection()) == null) {
            try {
                // 线程等待
                wait(timeout);
            } catch (InterruptedException e) {
            }
            if (System.currentTimeMillis() - startTime >= timeout) {
                // 如果超时, 则返回
                return null;
            }
        }
        return connection;
    }

    /**
     * 关闭所有连接
     */
    @Override
    protected synchronized void release() {
        try {
            // 将当前连接赋值到枚举中
            Enumeration<Connection> allConnections = this.freeConnections.elements();
            // 使用循环关闭所有连接
            while (allConnections.hasMoreElements()) {
                // 如果此枚举对象至少还有一个可提供的元素, 则返回此枚举的下一个元素
                Connection connection = allConnections.nextElement();
                try {
                    connection.close();
                    num--;
                } catch (SQLException e) {
                    System.out.println("无法关闭连接池中的连接,原因:" + e.getMessage());
                }
            }
            this.freeConnections.removeAllElements();
            numActive = 0;
        } finally {
            super.release();
        }
    }

    /**
     * 建立连接池
     */
    @Override
    public void createPool() {
        pool = new DBConnectionPool();
        if (pool != null) {
            System.out.println("创建连接池成功");
        } else {
            System.out.println("创建连接池失败");
        }
    }
}
