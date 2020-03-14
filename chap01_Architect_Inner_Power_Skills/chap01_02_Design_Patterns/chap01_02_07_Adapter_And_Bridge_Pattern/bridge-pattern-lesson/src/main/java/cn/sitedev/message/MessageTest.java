package cn.sitedev.message;

public class MessageTest {
    public static void main(String[] args) {
        IMessage message = new SmsMessage();
        AbstractMessage abstractMessage = new NormalMessage(message);
        abstractMessage.sendMessage("加班申请速批", "王总");

        message = new EmailMessage();
        abstractMessage = new UrgencyMessage(message);
        abstractMessage.sendMessage("加班申请速批", "王总");
    }
}
