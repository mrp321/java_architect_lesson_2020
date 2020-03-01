package cn.sitedev.breaksingleton.reflect;

import cn.sitedev.registry.enumeration.EnumSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumSingletonBreakTest {
    public static void main(String[] args) {
        try {
            Class clazz = EnumSingleton.class;
            Constructor constructor = clazz.getDeclaredConstructor(null);
            constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
