package cn.sitedev.course;

public interface CourseAggregate {
    void add(Course course);

    void remove(Course course);

    Iterator<Course> iterator();
}
