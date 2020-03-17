package cn.sitedev.player;

public class SpeedAction implements IAction {
    private GPlayer gPlayer;

    public SpeedAction(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void execute() {
        this.gPlayer.speed();
    }
}
