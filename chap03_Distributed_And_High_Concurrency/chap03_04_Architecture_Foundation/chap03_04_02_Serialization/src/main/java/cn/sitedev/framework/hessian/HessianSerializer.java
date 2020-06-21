package cn.sitedev.framework.hessian;

import cn.sitedev.framework.ISerializer;
import cn.sitedev.framework.User;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
        try {
            hessianOutput.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        try {
            return (T) hessianInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        HessianSerializer serializer = new HessianSerializer();
        byte[] bytes = serializer.serialize(new User("sitedev", 18));
        User user = serializer.deserialize(bytes,null);
        System.out.println(user);
    }
}
