package cn.sitedev.framework.protobuf;

public class ProtoBufPrint {
    public static void main(String[] args) {
        ProtobufSerializer serializer = new ProtobufSerializer();
        UserProtos.User user = UserProtos.User.newBuilder().setAge(300).setName("Mic").build();
        byte[] bytes = serializer.serialize(user);
        for (byte bt : bytes) {
            System.out.print(bt + " ");
        }
    }
}
