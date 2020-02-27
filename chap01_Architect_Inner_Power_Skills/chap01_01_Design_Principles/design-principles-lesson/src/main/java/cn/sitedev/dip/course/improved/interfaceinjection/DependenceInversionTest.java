package cn.sitedev.dip.course.improved.interfaceinjection;

import cn.sitedev.dip.course.improved.JavaCourse;
import cn.sitedev.dip.course.improved.PythonCourse;

public class DependenceInversionTest {
    public static void main(String[] args) {
        Tom tom = new Tom();
        tom.study(new JavaCourse());
        tom.study(new PythonCourse());
    }
}
