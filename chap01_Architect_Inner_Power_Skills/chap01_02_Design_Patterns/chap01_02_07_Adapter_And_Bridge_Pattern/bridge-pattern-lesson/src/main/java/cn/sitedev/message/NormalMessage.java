package cn.sitedev.message;

/**
 * 普通消息类
 */
public class NormalMessage extends AbstractMessage {

    /**
     * 构造方法, 传入实现部分的对象
     *
     * @param message
     */
    public NormalMessage(IMessage message) {
        super(message);
    }

    @Override
    public void sendMessage(String message, String toUser) {
        // 对于普通消息, 直接调用父类方法, 发送消息即可
        super.sendMessage(message, toUser);
    }
}
