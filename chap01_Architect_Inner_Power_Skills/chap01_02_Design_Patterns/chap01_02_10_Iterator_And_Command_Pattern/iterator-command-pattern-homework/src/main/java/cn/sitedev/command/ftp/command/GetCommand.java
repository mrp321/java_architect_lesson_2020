package cn.sitedev.command.ftp.command;

import cn.sitedev.command.ftp.FtpServer;

public class GetCommand implements ICommand {
    private FtpServer ftpServer;

    public GetCommand(FtpServer ftpServer) {
        this.ftpServer = ftpServer;
    }

    @Override
    public void execute() {
        ftpServer.get();
    }
}
