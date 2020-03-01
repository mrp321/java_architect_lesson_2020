package cn.sitedev.breaksingleton.serialize;

import java.io.Serializable;

/**
 * 反射导致破坏单例
 */
public class SerializableSingleton implements Serializable {
    // 序列化就是把内存中的状态通过转换成字节码的形式
    // 从而转换成一个IO流, 写入其他地方(可以是磁盘, 网络IO)
    // 内存中的状态会被永久保存下来

    // 反序列化就是将已经持久化的字节码内容转换为IO流
    // 通过IO流的读取, 进而将读取的内容转换成Java对象
    // 在转换过程中会重新创建对象

    private static final SerializableSingleton INSTANCE = new SerializableSingleton();

    private SerializableSingleton() {
    }

    public static SerializableSingleton getInstance() {
        return INSTANCE;
    }
}
