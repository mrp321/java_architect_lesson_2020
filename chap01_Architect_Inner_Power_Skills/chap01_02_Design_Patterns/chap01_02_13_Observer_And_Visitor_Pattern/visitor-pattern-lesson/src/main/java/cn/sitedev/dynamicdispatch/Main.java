package cn.sitedev.dynamicdispatch;

public class Main {
    public static void main(String[] args) {
        Person man = new Man();
        Person woman = new Woman();

        man.test();
        woman.test();
    }
}

interface Person {
    void test();
}

class Man implements Person {

    @Override
    public void test() {
        System.out.println("man");
    }
}

class Woman implements Person {
    @Override
    public void test() {
        System.out.println("woman");
    }
}
