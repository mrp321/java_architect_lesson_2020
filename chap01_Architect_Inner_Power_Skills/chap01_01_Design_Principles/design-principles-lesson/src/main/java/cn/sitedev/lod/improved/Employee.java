package cn.sitedev.lod.improved;

import cn.sitedev.lod.Course;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public void checkNumberOfCourses() {
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courseList.add(new Course());
        }
        System.out.println("目前已发布的课程数量是:" + courseList.size());
    }
}
