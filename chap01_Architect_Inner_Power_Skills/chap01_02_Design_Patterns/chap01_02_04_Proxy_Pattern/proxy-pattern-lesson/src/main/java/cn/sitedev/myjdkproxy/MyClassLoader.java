package cn.sitedev.myjdkproxy;

import lombok.Cleanup;

import java.io.*;

public class MyClassLoader extends ClassLoader {
    private File classPathFile;

    public MyClassLoader() {
        String classPath = MyClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    public Class<?> findClass(String name) {
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if (classPathFile != null) {
            File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
            if (classFile != null) {
                if (classFile.exists()) {
                    try {
                        @Cleanup FileInputStream fileInputStream = new FileInputStream(classFile);
                        @Cleanup ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] buff = new byte[1024];
                        int len;
                        while ((len = fileInputStream.read(buff)) > 0) {
                            byteArrayOutputStream.write(buff, 0, len);
                        }
                        return defineClass(className, byteArrayOutputStream.toByteArray(), 0,
                                byteArrayOutputStream.size());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
