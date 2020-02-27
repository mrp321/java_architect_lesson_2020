package cn.sitedev.srp.course;

public class Course {
    public void study(String courseName) {
        if ("直播课".equals(courseName)) {
            System.out.println("不能快进");
        } else {
            System.out.println("可以任意来回播放");
        }
    }
}
