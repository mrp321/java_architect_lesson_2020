package cn.sitedev.framework;

public interface ISerializer {
    <T> byte[] serialize(T object);
    <T> T deserialize(byte[] data, Class<T> clazz);
}
