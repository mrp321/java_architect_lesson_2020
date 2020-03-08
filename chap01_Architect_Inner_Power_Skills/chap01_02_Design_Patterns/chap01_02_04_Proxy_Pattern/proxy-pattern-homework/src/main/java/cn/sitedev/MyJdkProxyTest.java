package cn.sitedev;

/**
 * 测试类
 */
public class MyJdkProxyTest {
    public static void main(String[] args) {
        TargetPrint targetPrint = (TargetPrint) new TargetInvocation().getInstance(new TargetImpl());
        System.out.println(targetPrint.getClass());
        targetPrint.print();
        targetPrint.print(0, 0, null, new TargetImpl());

        TargetTest targetTest = (TargetTest) new TargetInvocation().getInstance(new TargetImpl());
        System.out.println(targetTest.getClass());
        targetTest.test();
        targetTest.test(0, 0, null, new TargetImpl());
    }
}
