package cn.sitedev.myjdkproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来生成源代码的工具类
 */
public class MyProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces,
                                          MyInvocationHandler invocationHandler) {
        try {
            // 动态生成.java文件
            String src = generateSrc(interfaces);

            // Java文件输出磁盘
            String filePath = MyProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy0.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();

            // 把生成的.java文件编译成.class文件
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            // 把编译生成的.class文件加载到jvm中
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor constructor = proxyClass.getConstructor(MyInvocationHandler.class);
            file.delete();

            // 返回字节码重组以后的新的代理对象
            return constructor.newInstance(invocationHandler);
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package cn.sitedev.myjdkproxy;").append(ln);
        sb.append("import cn.sitedev.myjdkproxy.IPerson;").append(ln);
        sb.append("import java.lang.reflect.*;").append(ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{").append(ln);
        sb.append("    MyInvocationHandler h;").append(ln);
        sb.append("    public $Proxy0(MyInvocationHandler h) {").append(ln);
        sb.append("        this.h = h;").append(ln);
        sb.append("    }").append(ln);
        for (Method method : interfaces[0].getMethods()) {
            Class<?>[] params = method.getParameterTypes();
            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();

            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " + paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");
                if (i > 0 && i < params.length - 1) {
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }
            sb.append("    public " + method.getReturnType().getName() + " " + method.getName() + "(" + paramNames.toString() + ") {").append(ln);
            sb.append("        try {").append(ln);
            sb.append("            Method method = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\", new Class[] {" + paramClasses.toString() + "});").append(ln);
            if (hasReturnValue(method.getReturnType())) {
                sb.append("            return ");
            } else {
                sb.append("            ");
            }
            sb.append(getCaseCode("this.h.invoke(this, method, new Object[] {" + paramValues + "})",
                    method.getReturnType()) + ";").append(ln);
            sb.append("        } catch (Exception e) {").append(ln);
            sb.append("        } catch (Throwable t) {").append(ln);
            sb.append("            throw new UndeclaredThrowableException(t);").append(ln);
            sb.append("        }").append(ln);
            sb.append("        " + getReturnEmptyCode(method.getReturnType())).append(ln);
            sb.append("    }").append(ln);
        }
        sb.append("}").append(ln);
        return sb.toString();
    }

    private static Map<Class, Class> mappings = new HashMap<>();

    static {
        mappings.put(int.class, Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "return 0;";
        } else if (returnClass == void.class) {
            return "";
        } else {
            return "return null";
        }
    }

    private static String getCaseCode(String code, Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "((" + mappings.get(returnClass).getName() + ")" + code + ")." + returnClass.getSimpleName() +
                    "Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz != void.class;
    }

    private static String toLowerFirstCase(String src) {
        char[] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
