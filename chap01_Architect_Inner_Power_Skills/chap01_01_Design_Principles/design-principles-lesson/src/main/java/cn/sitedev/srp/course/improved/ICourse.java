package cn.sitedev.srp.course.improved;

public interface ICourse {
    /**
     * 获得基本信息
     *
     * @return
     */
    String getCourseName();

    /**
     * 获得视频流
     *
     * @return
     */
    byte[] getCourseVideo();

    /**
     * 学习课程
     */
    void studyCourse();

    /**
     * 退款
     */
    void refundCourse();
}
