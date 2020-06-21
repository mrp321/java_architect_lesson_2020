package cn.sitedev.framework.json;

import cn.sitedev.framework.ISerializer;
import cn.sitedev.framework.User;
import com.alibaba.fastjson.JSON;

public class FastJsonSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(new String(data), clazz);
    }

    public static void main(String[] args) {
        FastJsonSerializer serializer = new FastJsonSerializer();
        byte[] bytes = serializer.serialize(new User("sitedev", 18));
        User user = serializer.deserialize(bytes, User.class);
        System.out.println(user);
    }
}
