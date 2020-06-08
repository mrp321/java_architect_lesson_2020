package cn.sitedev.objectfactory;

import cn.sitedev.domain.Blog;

public class ObjectFactoryTest {
    public static void main(String[] args) {
        MyObjectFactory myObjectFactory = new MyObjectFactory();
        Blog blog = (Blog) myObjectFactory.create(Blog.class);
        System.out.println(blog);
    }
}
