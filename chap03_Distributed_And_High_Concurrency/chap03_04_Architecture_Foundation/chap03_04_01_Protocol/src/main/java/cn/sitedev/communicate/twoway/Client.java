package cn.sitedev.communicate.twoway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            // 向本机8080端口发送请求
            Socket socket = new Socket("127.0.0.1", 8080);
            // 由系统标准输入设备构造BufferedReader对象
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            // 由Socket对象得到输出流, 并构造PrintWriter对象
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            // 由Socket对象得到输入流, 并构造相应的BufferedReader对象
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 从系统标准输入读入一个字符串
            String readLine = sin.readLine();
            // 若从标准输入读入的字符串为"bye", 则停止循环
            while (!readLine.equals("bye")) {
                // 从系统标准输入读入的字符串输出到Server
                os.println(readLine);
                // 刷新输出流, 使Server马上收到该字符串
                os.flush();
                // 在系统标准输出上打印读入的字符串
                System.out.println("Client: " + readLine);
                // 从Server读入一个字符串, 并打印到标准输出上
                System.out.println("Server: " + is.readLine());
                // 从系统标准输入读入一个字符串
                readLine = sin.readLine();
            } // 继续循环
            os.close();
            is.close();
            socket.close();

        } catch (UnknownHostException e) {
            System.out.println("Error:" + e);
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }
}
