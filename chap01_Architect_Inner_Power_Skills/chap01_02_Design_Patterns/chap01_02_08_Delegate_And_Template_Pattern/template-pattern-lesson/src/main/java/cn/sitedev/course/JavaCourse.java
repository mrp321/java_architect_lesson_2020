package cn.sitedev.course;

public class JavaCourse extends AbstractCourse {
    private boolean needCheckHomework = false;

    @Override
    protected void checkHomework() {
        System.out.println("检查Java作业");
    }

    public void setNeedCheckHomework(boolean needCheckHomework) {
        this.needCheckHomework = needCheckHomework;
    }

    @Override
    protected boolean needCheckHomework() {
        return this.needCheckHomework;
    }
}
