package cn.sitedev.player;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<IAction> actions = new ArrayList<>();

    public void addAction(IAction action) {
        this.actions.add(action);
    }

    public void execute(IAction action) {
        action.execute();
    }

    public void executeAll() {
        for (IAction action : this.actions) {
            action.execute();
        }
        this.actions.clear();
    }
}
