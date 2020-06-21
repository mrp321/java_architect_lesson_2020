package cn.sitedev.framework.protobuf;

import cn.sitedev.framework.ISerializer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T object) {
        ByteString bytes =((UserProtos.User) object).toByteString();
        return bytes.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        UserProtos.User user = null;
        try {
            user = UserProtos.User.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return (T) user;
    }

    public static void main(String[] args) {
        ProtobufSerializer serializer = new ProtobufSerializer();
        UserProtos.User user = UserProtos.User.newBuilder().setName("Mic").setAge(18).build();
       byte[] bytes =  serializer.serialize(user);
        System.out.println(bytes);

        UserProtos.User userDeserialize = serializer.deserialize(bytes,null);
        System.out.println(userDeserialize);
    }
}
