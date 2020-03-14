package cn.sitedev.interfaceadapter;

public class InterfaceAdapterTest {
    public static void main(String[] args) {
        DC dc = new PowerAdapter(new AC220());
        dc.output5V();
    }
}
