package cn.sitedev.abstractfactory;

public class JavaCourseFactory extends CourseFactory {
    @Override
    protected INote createNote() {
        return new JavaNote();
    }

    @Override
    protected IVideo createVideo() {
        return new JavaVideo();
    }
}
