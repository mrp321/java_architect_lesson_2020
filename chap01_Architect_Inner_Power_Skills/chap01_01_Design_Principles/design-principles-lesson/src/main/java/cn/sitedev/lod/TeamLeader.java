package cn.sitedev.lod;

import java.util.ArrayList;
import java.util.List;

public class TeamLeader {
    public void commandCheckNumber(Employee employee) {
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courseList.add(new Course());
        }
        employee.checkNumberOfCourses(courseList);
    }
}
