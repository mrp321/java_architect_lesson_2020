package cn.sitedev;

/**
 * 包含test方法的接口
 */
public interface TargetTest {
    int test();

    byte test(byte x);

    char test(char x);

    long test(long x);

    double test(int x, double y);

    String test(int x, double y, String z);

    TargetTest test(int x, double y, String z, TargetPrint targetPrint);

}
