package cn.sitedev.breaksingleton.reflect;

import cn.sitedev.registry.enumeration.EnumSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumSingletonBreakTest2 {
    public static void main(String[] args) {
        try {
            Class clazz = EnumSingleton.class;
            Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            constructor.newInstance("Sitedev", 10);
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
