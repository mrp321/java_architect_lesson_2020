package cn.sitedev.player;

public class PlayAction implements IAction {
    private GPlayer gPlayer;

    public PlayAction(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void execute() {
        this.gPlayer.play();
    }
}
