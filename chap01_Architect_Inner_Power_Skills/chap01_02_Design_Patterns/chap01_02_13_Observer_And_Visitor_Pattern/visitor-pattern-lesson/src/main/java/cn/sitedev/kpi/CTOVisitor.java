package cn.sitedev.kpi;

// CTO 访问者
public class CTOVisitor implements IVisitor {
    @Override
    public void visit(Engineer engineer) {
        System.out.println("工程师:" + engineer.name + ", 代码行数:" + engineer.getCodeLines());
    }

    @Override
    public void visit(Manager manager) {
        System.out.println("经理:" + manager.name + ", 新产品数量:" + manager.getProducts());
    }
}
