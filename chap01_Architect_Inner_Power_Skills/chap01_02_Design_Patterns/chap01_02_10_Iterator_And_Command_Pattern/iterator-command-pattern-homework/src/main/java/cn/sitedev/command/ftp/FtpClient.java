package cn.sitedev.command.ftp;

import cn.sitedev.command.ftp.command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class FtpClient {
    private List<ICommand> commands = new ArrayList<>();

    public void addCommand(ICommand command) {
        this.commands.add(command);
    }

    public void removeCommand(ICommand command) {
        this.commands.remove(command);
    }

    public void call(ICommand command) {
        command.execute();
    }

    public void callAll() {
        for (ICommand command : commands) {
            call(command);
        }
    }
}
