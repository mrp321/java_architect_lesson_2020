package cn.sitedev.communicate.twoway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            // 监听8080端口
            serverSocket = new ServerSocket(8080);
            // 阻塞等待客户请求
            // 有客户请求到来, 则会产生一个Socket对象, 并继续执行
            socket = serverSocket.accept();

            String line = null;
            // 由Socket对象得到输入流, 并构造相应的BufferedReader对象
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 由Socket对象得到输出流, 并构造PrintWriter对象
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            // 由系统标准输入设备构造BufferedReader对象
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            // 在标准输出上打印从客户端读入的字符串
            System.out.println("Client: " + is.readLine());

            // 从标准输入读入一个字符串
            line = sin.readLine();
            // 如果该字符串为bye, 则停止循环
            while (!line.equals("bye")) {
                // 向客户端输出该字符串
                os.println(line);
                // 刷新输出流, 使Client马上收到该字符串
                os.flush();
                // 在系统标准输出上打印读入的字符串
                System.out.println("Server: " + line);
                // 从Client读入一个字符串, 并打印到标准输出上
                System.out.println("Client: " + is.readLine());
                // 从标准输入读入一个字符串
                line  = sin.readLine();
            } // 继续循环

            // 释放资源
            os.close();
            is.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
