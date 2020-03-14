package cn.sitedev.classadapter;

public class ClassAdapterTest {
    public static void main(String[] args) {
        DC5 adapter = new PowerAdapter();
        adapter.outputDC5V();
    }
}
