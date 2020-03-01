package cn.sitedev.breaksingleton.serialize;

import java.io.*;

public class SerializableSingletonTest {
    public static void main(String[] args) {
        Object s2 = SerializableSingleton.getInstance();
        testSerialize(s2);

        System.out.println("===================");
        // 测试优化后的单例是否能够被反序列化
        s2 = cn.sitedev.breaksingleton.serialize.improved.SerializableSingleton.getInstance();
        testSerialize(s2);
    }

    private static void testSerialize(Object s2) {
        Object s1 = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 将对象s2进行序列化操作
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(s2);

            // 将对象s2进行反序列化操作, 读取出来的对象赋值给s1
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            s1 = objectInputStream.readObject();

            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);
            System.out.println("s1 == s2 ? " + (s1 == s2));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
