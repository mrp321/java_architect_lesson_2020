package cn.sitedev.jdkproxy;

import cn.sitedev.dynamicproxy.IPerson;
import cn.sitedev.dynamicproxy.JdkMeipo;
import cn.sitedev.dynamicproxy.ZhaoLiu;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JDKProxyTest {
    public static void main(String[] args) {
        try {
            IPerson obj = new JdkMeipo().getInstance(new ZhaoLiu());
            obj.findLove();

            // 通过反编译工具可以查看源代码
            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IPerson.class});

            String fileOut = "E://$Proxy0.class";
            System.out.println("class文件输出路径:" + fileOut);
            FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
