package cn.sitedev.employment;

public class Boss {
    public void command(String task, Leader leader) {
        leader.doing(task);
    }
}
