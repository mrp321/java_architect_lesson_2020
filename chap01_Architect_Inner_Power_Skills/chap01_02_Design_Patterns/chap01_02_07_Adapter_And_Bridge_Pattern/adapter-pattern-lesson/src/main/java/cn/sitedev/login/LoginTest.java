package cn.sitedev.login;

public class LoginTest {
    public static void main(String[] args) {
        PassportForThirdAdapter adapter = new PassportForThirdAdapter();
        adapter.login("sitedev", "sitedev");
        adapter.login4QQ("sitedev");
        adapter.login4Wechat("sitedev");
    }
}
