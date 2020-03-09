package cn.sitedev.pancake.v2;

public class EggDecorator extends PancakeDecorator {
    public EggDecorator(Pancake pancake) {
        super(pancake);
    }

    @Override
    protected void doSomething() {

    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}
