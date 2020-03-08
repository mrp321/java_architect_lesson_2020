package cn.sitedev.common;

public class RealSubject implements ISubject {
    @Override
    public void request() {
        System.out.println("RealSubject.request");
    }
}
