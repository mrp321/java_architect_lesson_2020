package cn.sitedev.breaksingleton.reflect;

import cn.sitedev.lazy.staticinnerclass.LazyInnerClassSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LazyInnerClassSingletonBreakTest {
    public static void main(String[] args) {
        try {
            Class<?> clazz = LazyInnerClassSingleton.class;
            testReflect(clazz);

            System.out.println("============================");
            //  静态内部类式单例优化后
            clazz = cn.sitedev.lazy.staticinnerclass.improved.LazyInnerClassSingleton.class;
            testReflect(clazz);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void testReflect(Class<?> clazz) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        // 通过反射获取私有的无参构造方法
        Constructor constructor = clazz.getDeclaredConstructor(null);
        // 强制访问
        constructor.setAccessible(true);
        //  创建实例
        Object o1 = constructor.newInstance();
        //  继续创建实例, 相当于第二次new
        Object o2 = constructor.newInstance();
        System.out.println("o1 == o2 ? " + (o1 == o2));
    }
}
