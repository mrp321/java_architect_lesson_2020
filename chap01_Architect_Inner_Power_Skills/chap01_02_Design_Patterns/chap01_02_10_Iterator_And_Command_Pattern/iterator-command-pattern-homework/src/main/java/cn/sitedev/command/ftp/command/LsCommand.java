package cn.sitedev.command.ftp.command;

import cn.sitedev.command.ftp.FtpServer;

public class LsCommand implements ICommand {
    private FtpServer ftpServer;

    public LsCommand(FtpServer ftpServer) {
        this.ftpServer = ftpServer;
    }

    @Override
    public void execute() {
        ftpServer.ls();
    }
}
