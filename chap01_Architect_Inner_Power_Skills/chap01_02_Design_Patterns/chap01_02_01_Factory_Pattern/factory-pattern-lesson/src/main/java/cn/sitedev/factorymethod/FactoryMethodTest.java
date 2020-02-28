package cn.sitedev.factorymethod;

public class FactoryMethodTest {
    public static void main(String[] args) {
        ICourseFactory factory = new JavaCourseFactory();
        factory.create().record();

        factory = new PythonCourseFactory();
        factory.create().record();
    }
}
