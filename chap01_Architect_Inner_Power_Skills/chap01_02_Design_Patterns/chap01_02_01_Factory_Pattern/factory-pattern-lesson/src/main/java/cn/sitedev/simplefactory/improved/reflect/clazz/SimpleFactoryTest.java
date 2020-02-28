package cn.sitedev.simplefactory.improved.reflect.clazz;

import cn.sitedev.simplefactory.JavaCourse;
import cn.sitedev.simplefactory.PythonCourse;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        CourseFactory courseFactory = new CourseFactory();
        courseFactory.create(JavaCourse.class).record();
        courseFactory.create(PythonCourse.class).record();
        courseFactory.create(null).record();
    }
}
