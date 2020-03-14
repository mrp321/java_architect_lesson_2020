package cn.sitedev.login2;

public class LoginTest2 {
    public static void main(String[] args) {
        IPassportForThird adapter = new PasswordForThirdAdapter();
        adapter.login4QQ("sitedev");
    }
}
