package cn.sitedev.command.ftp.command;

import cn.sitedev.command.ftp.FtpServer;

public class SendCommand implements ICommand {
    private FtpServer ftpServer;

    public SendCommand(FtpServer ftpServer) {
        this.ftpServer = ftpServer;
    }

    @Override
    public void execute() {
        ftpServer.send();
    }
}
