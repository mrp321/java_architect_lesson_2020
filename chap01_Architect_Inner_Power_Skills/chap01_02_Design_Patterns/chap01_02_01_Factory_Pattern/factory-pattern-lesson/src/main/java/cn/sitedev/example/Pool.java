package cn.sitedev.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 自定义连接池 getInstance()返回 POOL 唯一实例,第一次调用时将执行构造函数
 * 构造函数 Pool()调用驱动装载 loadDrivers()函数;连接池创建 createPool()函数
 * loadDrivers()装载驱动
 * createPool()建连接池
 * getConnection()返回一个连接实例
 * getConnection(long time)添加时间限制
 * freeConnection(Connection con)将 con 连接实例返回到连接池
 * getnum()返回空闲连接数
 * getnumActive()返回当前使用的连接数
 */
public abstract class Pool {
    /**
     * 属性文件名
     */
    public String propertiesName = "connection-INF.properties";

    /**
     * 定义唯一实例
     */
    private static Pool instance = null;

    /**
     * 最大连接数
     */
    protected int maxConnect = 100;

    /**
     * 保持连接数
     */
    protected int normalConnect = 10;

    /**
     * 驱动字符串
     */
    protected String driverName = null;

    /**
     * 驱动类
     */
    protected Driver driver = null;

    /**
     * 私有构造器, 不允许外界访问
     */
    protected Pool() {
        try {
            init();
            loadDrivers(driverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化所有从配置文件中读取的成员变量
     *
     * @throws IOException
     */
    private void init() throws IOException {
        InputStream inputStream = Pool.class.getResourceAsStream(propertiesName);
        Properties properties = new Properties();
        properties.load(inputStream);
        this.driverName = properties.getProperty("driverName");
        this.maxConnect = Integer.parseInt(properties.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(properties.getProperty("normalConnect"));
    }

    /**
     * 装载和注册所有JDBC驱动程序
     *
     * @param driverName 驱动字符串
     */
    protected void loadDrivers(String driverName) {
        try {
            this.driver = (Driver) Class.forName(driverName).newInstance();
            DriverManager.registerDriver(driver);
            System.out.println("成功注册JDBC驱动程序" + driverName);
        } catch (Exception e) {
            System.out.println("无法注册JDBC驱动程序" + driverName + ", 错误:" + e.getMessage());
        }
    }

    /**
     * 创建连接池
     */
    public abstract void createPool();

    /**
     * 单例模式返回数据库连接池Pool的实例
     *
     * @return
     * @throws Exception
     */
    public static synchronized Pool getInstance() throws Exception {
        if (instance == null) {
            instance.init();
            instance = (Pool) Class.forName("cn.sitedev.example.other.Pool").newInstance();
        }
        return instance;
    }

    /**
     * 获得一个可用的连接, 如果没有则创建一个连接, 且小于最大连接限制
     *
     * @return
     */
    public abstract Connection getConnection();

    /**
     * 获得一个连接, 有时间限制
     *
     * @param time 设置该连接的持续时间(以ms为单位)
     * @return
     */
    public abstract Connection getConnection(long time);

    /**
     * 将连接对象返回给连接池
     *
     * @param connection 连接对象
     */
    public abstract void freeConnection(Connection connection);

    /**
     * 返回当前空闲连接数
     *
     * @return
     */
    public abstract int getnum();

    /**
     * 返回当前工作的连接数
     *
     * @return
     */
    public abstract int getnumActive();

    /**
     * 关闭所有连接,撤销驱动注册(此方法为单例方法)
     */
    protected synchronized void release() {
        // 撤销驱动
        try {
            DriverManager.deregisterDriver(this.driver);
            System.out.println("撤销JDBC驱动程序:" + driver.getClass().getName());
        } catch (SQLException e) {
            System.out.println("无法撤销JDBC驱动程序的注册:" + driver.getClass().getName() + ", 原因:" + e.getMessage());
        }
    }
}
