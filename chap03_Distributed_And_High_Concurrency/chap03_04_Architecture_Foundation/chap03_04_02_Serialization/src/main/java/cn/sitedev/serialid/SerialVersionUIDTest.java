package cn.sitedev.serialid;

import lombok.Cleanup;

import java.io.*;

public class SerialVersionUIDTest {
    public static void main(String[] args) {
//        serialize();
        deserialize();
    }

    private static void serialize() {
        // 序列化
        // Java对象 -> 字节数组
        try {
            @Cleanup FileOutputStream fileOutputStream = new FileOutputStream("user.object");
            @Cleanup  ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new User("sitedev"));
            objectOutputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void deserialize() {
        // 反序列化
        // 字节数组 -> Java对象
        try {
           @Cleanup FileInputStream fileInputStream = new FileInputStream("user.object");
            @Cleanup ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            User user = (User) objectInputStream.readObject();
            System.out.println(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
