package cn.sitedev.chatroom;

public class MediatorTest {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User user = new User("user1", chatRoom);
        User user2 = new User("user2", chatRoom);

        user.sendMsg("I am User1");
        user2.sendMsg("I am User2");
    }
}
