package cn.sitedev.command.ftp.command;

import cn.sitedev.command.ftp.FtpServer;

public class CdCommand implements ICommand {
    private FtpServer ftpServer;

    public CdCommand(FtpServer ftpServer) {
        this.ftpServer = ftpServer;
    }

    @Override
    public void execute() {
        ftpServer.cd();
    }
}
