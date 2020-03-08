package cn.sitedev.common;

public class Proxy implements ISubject {
    private ISubject subject;

    public Proxy(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        before();
        this.subject.request();
        after();
    }

    private void before() {
        System.out.println("Proxy.before");
    }

    private void after() {
        System.out.println("Proxy.after");
    }
}
