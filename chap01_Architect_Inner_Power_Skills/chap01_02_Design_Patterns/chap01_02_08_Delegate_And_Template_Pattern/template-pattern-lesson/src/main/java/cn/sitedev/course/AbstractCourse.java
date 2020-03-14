package cn.sitedev.course;

public abstract class AbstractCourse {
    public final void createCourse() {
        // 1. 发布预习资料
        postPreResource();
        // 2. 制作课件
        createPPT();
        // 3. 直播授课
        liveVideo();
        // 4. 上传课后资料
        postResource();
        // 5. 布置作业
        postHomework();

        if (needCheckHomework()) {
            checkHomework();
        }
    }

    protected void postPreResource() {
        System.out.println("发布预习资料");
    }

    protected void createPPT() {
        System.out.println("上传课件");
    }

    protected void liveVideo() {
        System.out.println("直播授课");
    }

    protected void postResource() {
        System.out.println("上传课后作业");
    }

    protected void postHomework() {
        System.out.println("布置作业");
    }

    /**
     * 钩子方法
     *
     * @return
     */
    protected boolean needCheckHomework() {
        return false;
    }

    protected abstract void checkHomework();

}
