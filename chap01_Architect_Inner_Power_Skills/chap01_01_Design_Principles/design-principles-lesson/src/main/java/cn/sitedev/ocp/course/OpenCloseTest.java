package cn.sitedev.ocp.course;

public class OpenCloseTest {
    public static void main(String[] args) {
        JavaCourse javaCourse = new JavaCourse(1, "Java架构师课程", 8200D);
        System.out.println("打折前:" + javaCourse);
        JavaDiscountCourse javaDiscountCourse = new JavaDiscountCourse(javaCourse.getId(), javaCourse.getName(),
                javaCourse.getPrice());
        System.out.println("打折后:" + javaDiscountCourse);
    }
}
