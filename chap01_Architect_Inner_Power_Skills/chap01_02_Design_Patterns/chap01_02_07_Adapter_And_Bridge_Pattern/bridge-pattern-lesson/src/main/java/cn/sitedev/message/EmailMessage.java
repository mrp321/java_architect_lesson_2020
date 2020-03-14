package cn.sitedev.message;

/**
 * 邮件短消息的实现类
 */
public class EmailMessage implements IMessage {
    @Override
    public void send(String message, String toUser) {
        System.out.printf("使用邮件短消息的方法, 发送消息%s 给 %s \n", message, toUser);
    }
}
