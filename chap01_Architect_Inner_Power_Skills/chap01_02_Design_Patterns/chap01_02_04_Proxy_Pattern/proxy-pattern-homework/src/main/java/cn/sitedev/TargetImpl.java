package cn.sitedev;

/**
 * 被代理类
 */
public class TargetImpl implements TargetPrint, TargetTest {
    @Override
    public void print() {
        System.out.println("public void print()");
    }

    @Override
    public void print(int x, double y, String z, TargetPrint targetPrint) {
        System.out.println("public void print(int x, double y, String z, Target target)");
    }

    @Override
    public int test() {
        System.out.println("public int test()");
        return 0;
    }

    @Override
    public byte test(byte x) {
        System.out.println("public byte test(byte x) {");
        return 0;
    }

    @Override
    public char test(char x) {
        System.out.println("public char test(char x)");
        return 0;
    }

    @Override
    public long test(long x) {
        System.out.println("public long test(long x)");
        return 0;
    }

    @Override
    public double test(int x, double y) {
        System.out.println("public double test(int x, double y)");
        return 0;
    }

    @Override
    public String test(int x, double y, String z) {
        System.out.println("public String test(int x, double y, String z)");
        return null;
    }

    @Override
    public TargetTest test(int x, double y, String z, TargetPrint targetPrint) {
        System.out.println("public Target test(int x, double y, String z, Target target)");
        return null;
    }
}
