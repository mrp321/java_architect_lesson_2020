package cn.sitedev.employment;

import java.util.HashMap;
import java.util.Map;

public class Leader implements IEmployee {
    private Map<String, IEmployee> employeeMap = new HashMap<>();

    public Leader() {
        employeeMap.put("编程", new EmployeeA());
        employeeMap.put("平面设计", new EmployeeB());
    }

    @Override
    public void doing(String task) {
        if (!employeeMap.containsKey(task)) {
            System.out.printf("\n这个任务%s超过我的能力范围\n", task);
            return;
        }
        employeeMap.get(task).doing(task);
    }
}
