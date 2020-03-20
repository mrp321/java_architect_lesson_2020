package cn.sitedev.club;

public class StateTest {
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.favorite();
        context.comment("好评!");
    }
}
