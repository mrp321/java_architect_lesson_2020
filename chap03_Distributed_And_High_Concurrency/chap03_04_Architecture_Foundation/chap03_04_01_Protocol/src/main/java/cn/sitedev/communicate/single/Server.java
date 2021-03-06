package cn.sitedev.communicate.single;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        BufferedReader in = null;
        try {
            // TCP的服务端要先监听一个端口,一般是先调用bind函数,给这个socket赋予一个IP地址和端口
            // 为什么需要端口呢?
            // 要知道, 你写的是一个应用程序.
            // 当一个网络包来的时候, 内核要通过TCP头里面的这个端口来找到这个应用程序, 把包给你
            // 为什么需要IP地址呢?
            // 有时候, 一台机器会有多个网卡, 也就是会有多个IP地址
            // 你可以选择监听所有网卡, 也可以选择监听一个网卡
            // 这样,只有发给这个网卡的包, 才会给你
            serverSocket = new ServerSocket(8080);
            // 阻塞等待客户端连接
            // 接下来, 服务端调用accept函数, 拿出一个已经完成的连接进行处理
            // 如果还没有完成, 就要进行等待
            Socket socket = serverSocket.accept();
            // 连接建立成功之后, 双方开始通过read和write函数来读写数据,就像往一个文件流里面写东西一样
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(in.readLine());
        } catch (IOException e) {
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
