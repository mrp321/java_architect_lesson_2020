package cn.sitedev.employment;

public class EmployeeA implements IEmployee {
    protected String goodAt = "编程";

    @Override
    public void doing(String task) {
        System.out.printf("我是员工A, 我擅长%s, 现在开始做%s工作", goodAt, task);
    }
}
