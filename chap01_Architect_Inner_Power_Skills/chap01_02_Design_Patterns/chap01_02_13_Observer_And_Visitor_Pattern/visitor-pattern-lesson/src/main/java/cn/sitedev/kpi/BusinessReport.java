package cn.sitedev.kpi;

import java.util.LinkedList;
import java.util.List;

// 员工业务报表类
public class BusinessReport {
    private List<Employee> employees = new LinkedList<>();

    public BusinessReport() {
        employees.add(new Manager("经理1"));
        employees.add(new Engineer("工程师1"));
        employees.add(new Engineer("工程师2"));
        employees.add(new Engineer("工程师3"));
        employees.add(new Manager("经理2"));
        employees.add(new Engineer("工程师4"));
    }

    /**
     * 为访问者展示报表
     *
     * @param visitor 公司高层,如CEO, CTO
     */
    public void showReport(IVisitor visitor) {
        for (Employee employee : employees) {
            employee.accept(visitor);
        }
    }
}
