package cn.sitedev.message;

/**
 * 实现消息发送的统一接口
 */
public interface IMessage {
    /**
     * 要发送的消息的内容和接收者
     *
     * @param message
     * @param toUser
     */
    void send(String message, String toUser);

}
