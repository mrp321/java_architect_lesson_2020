package cn.sitedev.general;

public class Abstraction {
    protected IImplementor iImplementor;

    public Abstraction(IImplementor iImplementor) {
        this.iImplementor = iImplementor;
    }

    public void operation() {
        this.iImplementor.operationImpl();
    }
}
