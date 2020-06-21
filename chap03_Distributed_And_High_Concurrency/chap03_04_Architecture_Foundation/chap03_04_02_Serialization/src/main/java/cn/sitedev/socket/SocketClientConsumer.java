package cn.sitedev.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientConsumer {
    public static void main(String[] args) {
        Socket socket = null;
        ObjectOutputStream out = null;

        try {
            socket = new Socket("127.0.0.1", 8080);
            User user = new User("Sitedev");

            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(user);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
