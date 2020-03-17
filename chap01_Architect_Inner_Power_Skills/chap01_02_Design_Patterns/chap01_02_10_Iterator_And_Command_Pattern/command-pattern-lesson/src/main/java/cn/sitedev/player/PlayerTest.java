package cn.sitedev.player;

public class PlayerTest {
    public static void main(String[] args) {
        GPlayer gPlayer = new GPlayer();
        Controller controller = new Controller();
        controller.execute(new PlayAction(gPlayer));

        controller.addAction(new PauseAction(gPlayer));
        controller.addAction(new PlayAction(gPlayer));
        controller.addAction(new StopAction(gPlayer));
        controller.addAction(new SpeedAction(gPlayer));
        controller.executeAll();
    }
}
