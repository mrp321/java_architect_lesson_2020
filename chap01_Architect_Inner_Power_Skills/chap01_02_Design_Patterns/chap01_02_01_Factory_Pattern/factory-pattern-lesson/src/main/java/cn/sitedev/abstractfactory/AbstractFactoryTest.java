package cn.sitedev.abstractfactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        CourseFactory factory = new JavaCourseFactory();
        factory.createVideo().record();
        factory.createNote().edit();

        factory = new PythonCourseFactory();
        factory.createVideo().record();
        factory.createNote().edit();
    }
}
