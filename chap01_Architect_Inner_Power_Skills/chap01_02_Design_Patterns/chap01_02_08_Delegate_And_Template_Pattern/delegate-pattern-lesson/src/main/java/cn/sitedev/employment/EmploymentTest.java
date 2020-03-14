package cn.sitedev.employment;

public class EmploymentTest {
    public static void main(String[] args) {
        new Boss().command("编程", new Leader());
        new Boss().command("修理电脑", new Leader());
    }
}
