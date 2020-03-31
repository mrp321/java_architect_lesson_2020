package cn.sitedev.chatroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private ChatRoom chatRoom;

    public void sendMsg(String msg) {
        this.chatRoom.showMsg(this, msg);
    }
}