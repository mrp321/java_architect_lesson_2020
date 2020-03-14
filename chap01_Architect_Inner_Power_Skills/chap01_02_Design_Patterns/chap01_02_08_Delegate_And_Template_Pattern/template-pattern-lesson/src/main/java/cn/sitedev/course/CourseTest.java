package cn.sitedev.course;

public class CourseTest {
    public static void main(String[] args) {
        System.out.println("======Java课程=====");
        JavaCourse javaCourse = new JavaCourse();
        javaCourse.setNeedCheckHomework(true);
        javaCourse.createCourse();

        System.out.println("========Python课程=========");
        PythonCourse pythonCourse = new PythonCourse();
        pythonCourse.createCourse();
    }
}
