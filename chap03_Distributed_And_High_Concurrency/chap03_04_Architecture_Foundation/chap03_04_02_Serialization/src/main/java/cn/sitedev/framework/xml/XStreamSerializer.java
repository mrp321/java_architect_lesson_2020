package cn.sitedev.framework.xml;

import cn.sitedev.framework.ISerializer;
import cn.sitedev.framework.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamSerializer implements ISerializer {
    private XStream xStream = new XStream(new DomDriver());

    @Override
    public <T> byte[] serialize(T object) {
        return xStream.toXML(object).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return (T) xStream.fromXML(new String(data));
    }

    public static void main(String[] args) {
        XStreamSerializer serializer = new XStreamSerializer();
        byte[] bytes = serializer.serialize(new User("sitedev", 18));
        User user = serializer.deserialize(bytes,null);
        System.out.println(user);
    }
}
