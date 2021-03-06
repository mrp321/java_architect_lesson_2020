package cn.sitedev.mebatis;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

public class Configuration {
    public static final ResourceBundle sqlMappings = ResourceBundle.getBundle("v1sql");

    public <T> T getMapper(Class<?> clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz},
                new MapperProxy(sqlSession));
    }
}
