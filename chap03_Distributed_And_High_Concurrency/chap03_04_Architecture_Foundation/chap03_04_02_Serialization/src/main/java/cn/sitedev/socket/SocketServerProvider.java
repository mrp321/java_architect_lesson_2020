package cn.sitedev.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerProvider {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        BufferedReader in = null;

        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User) objectInputStream.readObject();
            System.out.println(user);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
