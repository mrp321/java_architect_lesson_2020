package cn.sitedev.demo.annotation.aspect;

public class LogAspect {
    // 在调用一个方法前, 执行before方法
    public void before() {
        System.out.println("Invoker Before Method...");
    }

    // 在调用一个方法后, 执行after方法
    public void after() {
        System.out.println("Invoker After Method...");
    }

    // 在调用一个方法发生异常时, 执行afterThrowing方法
    public void afterThrowing() {
        System.out.println("Invoker AfterThowing Method...");
    }
}
