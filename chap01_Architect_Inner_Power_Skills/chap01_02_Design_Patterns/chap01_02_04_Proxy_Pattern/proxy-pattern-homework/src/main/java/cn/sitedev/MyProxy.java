package cn.sitedev;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义代理类
 */
public class MyProxy {
    /**
     * 换行符
     */
    public static final String LN = System.getProperty("line.separator");

    /**
     * 存放基本数据类型到包装数据类型的映射
     */
    private static Map<Class, Class> mappings = new HashMap<>();

    static {
        mappings.put(char.class, Character.class);
        mappings.put(byte.class, Byte.class);
        mappings.put(short.class, Short.class);
        mappings.put(int.class, Integer.class);
        mappings.put(long.class, Long.class);
        mappings.put(float.class, Float.class);
        mappings.put(double.class, Double.class);
    }

    /**
     * 创建代理对象
     *
     * @param classLoader
     * @param interfaces
     * @param invocationHandler
     * @return
     */
    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces,
                                          MyInvocationHandler invocationHandler) {
        try {
            // 动态生成.java文件
            String srcCode = generateSrc(interfaces);

            // .java文件输出至磁盘
            String filePath = MyProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy0.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(srcCode);
            fileWriter.flush();
            fileWriter.close();

            // 把生成.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            // 把编译生成的.class文件加载到jvm中
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor constructor = proxyClass.getConstructor(MyInvocationHandler.class);

            // 把之前生成的.java文件删除
//            file.delete();

            // 返回字节码重组以后的新的代理对象
            return constructor.newInstance(invocationHandler);
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成源码
     *
     * @param interfaces
     * @return
     */
    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer srcCode = new StringBuffer();
        // 生成package语句
        srcCode.append(getPackageCode()).append(LN);
        // 生成import语句
        srcCode.append(getImportCode(interfaces));
        srcCode.append("import java.lang.reflect.*;").append(LN);
        // 生成implements
        srcCode.append("public class $Proxy0 " + getImplementsCode(interfaces) + " {").append(LN);
        // 成员变量
        srcCode.append("    private MyInvocationHandler h;").append(LN);
        // 构造器
        srcCode.append("    public $Proxy0(MyInvocationHandler h) {").append(LN);
        srcCode.append("        this.h = h;").append(LN);
        srcCode.append("    }").append(LN);
        // 方法
        srcCode.append(getAllInterfacesMethodsCode(interfaces));
        srcCode.append("}");
        return srcCode.toString();
    }

    /**
     * 生成package语句
     *
     * @return
     */
    private static String getPackageCode() {
        return "package " + MyProxy.class.getPackage().getName() + ";";
    }

    /**
     * 生成import语句
     *
     * @param interfaces
     * @return
     */
    private static StringBuffer getImportCode(Class<?>[] interfaces) {
        StringBuffer srcCode = new StringBuffer();
        for (Class interfaceCls : interfaces) {
            srcCode.append("import " + interfaceCls.getName() + ";").append(LN);
        }
        return srcCode;
    }

    /**
     * 生成implements语句片段
     *
     * @param interfaces
     * @return
     */
    private static StringBuffer getImplementsCode(Class<?>[] interfaces) {
        StringBuffer srcCode = new StringBuffer(" implements ");
        // 去除"[", "]", 以及"interface"
        srcCode.append(Arrays.toString(interfaces).replaceAll("(\\[|\\]|interface)", ""));
        return srcCode;
    }

    /**
     * 生成所有接口的方法实现代码
     *
     * @param interfaces
     * @return
     */
    private static StringBuffer getAllInterfacesMethodsCode(Class<?>[] interfaces) {
        StringBuffer srcCode = new StringBuffer();
        // 遍历所有实现的接口
        for (Class<?> interfaceCls : interfaces) {
            // 生成指定接口的方法实现代码
            srcCode.append(getEachInterfaceMethodsCode(interfaceCls));
        }
        return srcCode;
    }

    private static StringBuffer getEachInterfaceMethodsCode(Class<?> interfaceCls) {
        StringBuffer srcCode = new StringBuffer();
        Class<?>[] params = null;
        Class<?> param = null;
        StringBuffer paramNames = null;
        StringBuffer paramValues = null;
        StringBuffer paramClasses = null;

        // 遍历接口中定义的所有方法
        for (Method method : interfaceCls.getMethods()) {
            paramNames = new StringBuffer();
            paramValues = new StringBuffer();
            paramClasses = new StringBuffer();
            params = method.getParameterTypes();
            for (int i = 0, j = params.length; i < j; i++) {
                param = params[i];
                String paramTypeName = param.getName();
                String paramSimpleName = toLowerFirstCase(param.getSimpleName()) + "Value";
                paramNames.append(paramTypeName + " " + paramSimpleName);
                paramValues.append(paramSimpleName);
                paramClasses.append(paramTypeName + ".class");
                if (i < j - 1) {
                    paramNames.append(", ");
                    paramClasses.append(", ");
                    paramValues.append(", ");
                }
            }
            srcCode.append("    public " + method.getReturnType().getName() + " " + method.getName() + "(" + paramNames.toString() + ") {").append(LN);
            srcCode.append("        try {").append(LN);
            srcCode.append("            Method method = " + interfaceCls.getName() + ".class.getMethod(\"" + method.getName() + "\", new Class[] {" + paramClasses.toString() + "});").append(LN);
            if (hasReturnValue(method.getReturnType())) {
                srcCode.append("            return ");
            } else {
                srcCode.append("            ");
            }
            srcCode.append(getCaseCode("this.h.invoke(this, method, new Object[] {" + paramValues.toString() + "})",
                    method.getReturnType()) + ";").append(LN);
            srcCode.append("        } catch(Throwable t) {").append(LN);
            srcCode.append("            t.printStackTrace();").append(LN);
            srcCode.append("        }").append(LN);
            srcCode.append("        " + getReturnEmptyCode(method.getReturnType())).append(LN);
            srcCode.append("    }").append(LN).append(LN);
        }
        return srcCode;
    }

    /**
     * 生成返回语句
     *
     * @param returnClass
     * @return
     */
    private static String getReturnEmptyCode(Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            String code = null;
            // 各种基本数据类型的默认值可能不同
            switch (returnClass.getName()) {
                case "char":
                    code = "return '\u0000';";
                    break;
                case "byte":
                case "short":
                case "int":
                    code = "return 0;";
                    break;
                case "long":
                    code = "return 0L;";
                    break;
                case "float":
                    code = "return 0.0F;";
                    break;
                case "double":
                    code = "return 0.0;";
                    break;
                default:
                    code = "return null;";
                    break;
            }
            return code;
        } else if (returnClass == void.class) {
            return "";
        } else {
            return "return null;";
        }
    }

    /**
     * 对于有返回值的方法, 会返回强制类型转换method.invoke(...)后的结果
     *
     * @param code
     * @param returnClass
     * @return
     */
    private static String getCaseCode(String code, Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "((" + mappings.get(returnClass).getName() + ")" + code + ")." + returnClass.getSimpleName() +
                    "Value()";
        } else if (returnClass != void.class) {
            return "((" + returnClass.getName() + ")" + code + ")";
        } else {
            return code;
        }
    }

    /**
     * 判断方法是否有返回值
     *
     * @param clazz
     * @return
     */
    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz != void.class;
    }

    /**
     * 首字母大写转小写
     *
     * @param src
     * @return
     */
    private static String toLowerFirstCase(String src) {
        return src.replaceFirst("[A-Z]", String.valueOf(src.charAt(0)).toLowerCase());
    }

}
