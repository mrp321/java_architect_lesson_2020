package cn.sitedev.myjdkproxy;

public class MyJdkProxyTest {
    public static void main(String[] args) {
        IPerson person = (IPerson) new MyMeipo().getInstance(new Customer());
        System.out.println(person.getClass());
        person.findLove();
    }
}
