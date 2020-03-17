package cn.sitedev.command.ftp;

import cn.sitedev.command.ftp.command.*;

public class FtpTest {
    public static void main(String[] args) {
        FtpServer server = new FtpServer();
        FtpClient client = new FtpClient();
        System.out.println("=============执行一个命令============");
        client.call(new LsCommand(server));

        System.out.println("=============执行多个命令============");
        client.addCommand(new CdCommand(server));
        client.addCommand(new DirCommand(server));
        client.addCommand(new GetCommand(server));
        client.addCommand(new PutCommand(server));
        client.addCommand(new SendCommand(server));
        client.callAll();
    }
}
