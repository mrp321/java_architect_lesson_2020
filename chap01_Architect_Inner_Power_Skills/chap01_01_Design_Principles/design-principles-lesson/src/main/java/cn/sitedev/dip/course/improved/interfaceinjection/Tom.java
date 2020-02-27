package cn.sitedev.dip.course.improved.interfaceinjection;

import cn.sitedev.dip.course.improved.ICourse;

public class Tom {
    public void study(ICourse course) {
        course.study();
    }
}
