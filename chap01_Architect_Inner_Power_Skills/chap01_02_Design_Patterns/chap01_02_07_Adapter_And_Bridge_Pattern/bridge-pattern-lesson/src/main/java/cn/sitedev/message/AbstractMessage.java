package cn.sitedev.message;

/**
 * 抽象消息类
 */
public abstract class AbstractMessage {
    /**
     * 持有一个实现部分的对象
     */
    private IMessage message;

    /**
     * 构造方法, 传入实现部分的对象
     *
     * @param message
     */
    public AbstractMessage(IMessage message) {
        this.message = message;
    }

    public void sendMessage(String message, String toUser) {
        this.message.send(message, toUser);
    }
}
