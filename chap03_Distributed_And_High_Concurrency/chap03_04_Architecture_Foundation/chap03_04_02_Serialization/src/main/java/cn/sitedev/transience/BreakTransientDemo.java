package cn.sitedev.transience;

import lombok.Cleanup;

import java.io.*;

public class BreakTransientDemo {
    public static void main(String[] args) {

        try {
            // 序列化
            // Java對象 -> 字節數組
            @Cleanup ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            @Cleanup ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(new User2("sitedev", 10, 1234.57, new User2("friend", 18, 34567.89, null)));

            // 反序列化
            // 字節數組 -> Java對象
            @Cleanup ByteArrayInputStream byteArrayInputStream =
                    new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            @Cleanup ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            User2 user = (User2) objectInputStream.readObject();
            System.out.println(user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
