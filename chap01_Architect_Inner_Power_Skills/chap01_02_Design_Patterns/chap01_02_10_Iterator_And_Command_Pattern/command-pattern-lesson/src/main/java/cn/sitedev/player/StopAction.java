package cn.sitedev.player;

public class StopAction implements IAction {
    private GPlayer gPlayer;

    public StopAction(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void execute() {
        this.gPlayer.stop();
    }
}
