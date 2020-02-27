package cn.sitedev.dip.course.improved.constructorinjection;

import cn.sitedev.dip.course.improved.JavaCourse;
import cn.sitedev.dip.course.improved.PythonCourse;

public class DependenceInversionTest {
    public static void main(String[] args) {
        Tom tom = new Tom(new JavaCourse());
        tom.study();
        tom = new Tom(new PythonCourse());
        tom.study();
    }
}
