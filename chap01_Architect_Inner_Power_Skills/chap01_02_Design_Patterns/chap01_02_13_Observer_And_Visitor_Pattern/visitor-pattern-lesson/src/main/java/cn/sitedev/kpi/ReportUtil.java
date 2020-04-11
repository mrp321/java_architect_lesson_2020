package cn.sitedev.kpi;

public class ReportUtil {
    public void visit(Employee employee) {
        if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            System.out.println("经理:" + manager.name + ", KPI:" + manager.kpi + ", 新产品数量:" + manager.getProducts());
        } else if (employee instanceof Engineer) {
            Engineer engineer = (Engineer) employee;
            System.out.println("工程师:" + engineer.name + ", KPI:" + engineer.kpi);
        }
    }
}
