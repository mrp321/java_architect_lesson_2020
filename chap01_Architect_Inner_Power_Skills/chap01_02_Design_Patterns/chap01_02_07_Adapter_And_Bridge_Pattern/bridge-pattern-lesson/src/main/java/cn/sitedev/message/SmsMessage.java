package cn.sitedev.message;

/**
 * 系统内短消息的实现类
 */
public class SmsMessage implements IMessage {
    @Override
    public void send(String message, String toUser) {
        System.out.printf("使用系统内短消息的方法, 发送消息 %s 给 %s \n", message, toUser);
    }
}
