package cn.sitedev.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 声明这是一个组件
@Component
// 声明这是一个切面Bean
@Aspect
public class AnnotationAspect {

    // 配置切入点, 该方法无方法体, 主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* cn.sitedev.service..*(..))")
    public void pointcut() {

    }

    // 配置前置通知, 使用在方法pointcut()上注册的切入点
    // 同时接收JoinPoint切入点对象, 可以没有该参数
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("前置通知: " + joinPoint);
    }

    // 配置后置通知, 使用在方法pointcut()上注册的切入点
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("后置通知: " + joinPoint);
    }

    // 配置环绕通知, 使用在方法pointcut()上注册的切入点
    @Around("pointcut()")
    public void around(JoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            System.out.println("环绕通知: " + joinPoint + ", 耗时: " + (end - start) + "ms");
        } catch (Throwable throwable) {
            long end = System.currentTimeMillis();
            System.out.println("环绕通知: " + joinPoint + ", 耗时: " + (end - start) + "ms, 异常信息: " + throwable.getMessage());
        }
    }

    // 配置后置返回通知, 使用在方法pointcut()上注册的切入点
    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("后置返回通知: " + joinPoint);
    }

    // 配置抛出异常后通知, 使用在方法pointcut()上注册的切入点
    @AfterThrowing(pointcut = "pointcut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        System.out.println("抛出异常后通知: " + joinPoint + ", 异常信息: " + exception.getMessage());
    }
}
