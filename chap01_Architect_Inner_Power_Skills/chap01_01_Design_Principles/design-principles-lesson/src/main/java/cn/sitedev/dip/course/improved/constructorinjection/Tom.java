package cn.sitedev.dip.course.improved.constructorinjection;

import cn.sitedev.dip.course.improved.ICourse;

public class Tom {
    private ICourse course;

    public Tom(ICourse course) {
        this.course = course;
    }

    public void study() {
        course.study();
    }
}
