package cn.sitedev.classadapter;

public class PowerAdapter extends AC220 implements DC5 {

    @Override
    public int outputDC5V() {
        int adapterInput = super.outputAC220V();
        int adapterOutput = adapterInput / 44;
        System.out.println("使用PowerAdapter输入AC" + adapterInput + "V, 输出DC" + adapterOutput + "V");
        return adapterOutput;
    }
}
