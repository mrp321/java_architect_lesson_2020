package cn.sitedev.auth.chain;

public class ChainTest {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        service.login("sitedev", "sitedev");
    }
}
