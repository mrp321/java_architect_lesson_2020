package cn.sitedev.command.ftp.command;

import cn.sitedev.command.ftp.FtpServer;

public class DirCommand implements ICommand {
    private FtpServer ftpServer;

    public DirCommand(FtpServer ftpServer) {
        this.ftpServer = ftpServer;
    }

    @Override
    public void execute() {
        ftpServer.dir();
    }
}
