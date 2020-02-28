package cn.sitedev.simplefactory.improved.reflect.str;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        CourseFactory courseFactory = new CourseFactory();
        courseFactory.create("cn.sitedev.simplefactory.JavaCourse").record();
        courseFactory.create("cn.sitedev.simplefactory.PythonCourse").record();
        courseFactory.create(null).record();
    }
}
