package cn.sitedev.command.ftp.command;

import cn.sitedev.command.ftp.FtpServer;

public class PutCommand implements ICommand {
    private FtpServer ftpServer;

    public PutCommand(FtpServer ftpServer) {
        this.ftpServer = ftpServer;
    }

    @Override
    public void execute() {
        ftpServer.put();
    }
}
