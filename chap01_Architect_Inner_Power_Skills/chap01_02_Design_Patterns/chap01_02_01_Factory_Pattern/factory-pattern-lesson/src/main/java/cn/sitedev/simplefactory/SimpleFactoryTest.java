package cn.sitedev.simplefactory;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        ICourse course = new JavaCourse();
        course.toString();
    }
}
