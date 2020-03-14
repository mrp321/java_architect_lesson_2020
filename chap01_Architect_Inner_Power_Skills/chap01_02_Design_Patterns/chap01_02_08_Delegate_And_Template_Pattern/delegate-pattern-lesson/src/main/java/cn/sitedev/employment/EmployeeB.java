package cn.sitedev.employment;

public class EmployeeB implements IEmployee {
    protected String goodAt = "平面设计";

    @Override
    public void doing(String task) {
        System.out.printf("我是员工B, 我擅长%s, 现在开始做%s工作", goodAt, task);
    }
}
