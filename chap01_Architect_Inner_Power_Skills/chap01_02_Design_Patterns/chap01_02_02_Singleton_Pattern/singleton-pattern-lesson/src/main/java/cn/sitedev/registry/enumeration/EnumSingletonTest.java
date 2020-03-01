package cn.sitedev.registry.enumeration;

import java.io.*;

public class EnumSingletonTest {
    public static void main(String[] args) {
        Object s1 = null;
        Object s2 = EnumSingleton.getInstance();

        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 将对象s2进行序列化, 即将对象转换为byte[]
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(s2);

            // 将对象s2进行反序列化, 即将byte[] 转换成对象, 并将该对象的引用赋值给s1
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
