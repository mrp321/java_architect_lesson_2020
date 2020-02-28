package cn.sitedev.simplefactory.improved;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        CourseFactory courseFactory = new CourseFactory();
        courseFactory.create("java").record();
        courseFactory.create("python").record();
        courseFactory.create("other").record();
    }
}
