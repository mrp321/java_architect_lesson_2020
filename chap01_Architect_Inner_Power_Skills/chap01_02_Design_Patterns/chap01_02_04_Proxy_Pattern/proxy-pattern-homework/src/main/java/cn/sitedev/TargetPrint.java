package cn.sitedev;

/**
 * 包含print方法的接口
 */
public interface TargetPrint {
    void print();

    void print(int x, double y, String z, TargetPrint targetPrint);
}
