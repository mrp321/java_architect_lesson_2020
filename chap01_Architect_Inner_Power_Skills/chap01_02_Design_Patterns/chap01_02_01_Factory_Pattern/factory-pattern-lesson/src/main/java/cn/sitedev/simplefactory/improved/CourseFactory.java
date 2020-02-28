package cn.sitedev.simplefactory.improved;

import cn.sitedev.simplefactory.ICourse;
import cn.sitedev.simplefactory.JavaCourse;
import cn.sitedev.simplefactory.PythonCourse;

public class CourseFactory {
    public ICourse create(String name) {
        if ("java".equals(name)) {
            return new JavaCourse();
        } else if ("python".equals(name)) {
            return new PythonCourse();
        } else {
            return null;
        }
    }
}
