package cn.sitedev.dip.course.improved.setterinjection;

import cn.sitedev.dip.course.improved.ICourse;

public class Tom {
    private ICourse course;

    public ICourse getCourse() {
        return course;
    }

    public void setCourse(ICourse course) {
        this.course = course;
    }

    public void study() {
        course.study();
    }
}
