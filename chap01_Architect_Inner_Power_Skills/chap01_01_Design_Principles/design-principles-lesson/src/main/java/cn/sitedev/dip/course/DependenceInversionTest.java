package cn.sitedev.dip.course;

public class DependenceInversionTest {
    public static void main(String[] args) {
        Tom tom = new Tom();
        tom.studyJavaCourse();
        tom.studyPythonCourse();
    }
}
