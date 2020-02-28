package cn.sitedev.simplefactory.improved.reflect.str;

import cn.sitedev.simplefactory.ICourse;

public class CourseFactory {
    public ICourse create(String className) {
        try {
            if (className != null && !className.isEmpty()) {
                return (ICourse) Class.forName(className).newInstance();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
