package cn.sitedev.composite;

import lombok.Data;

@Data
public class Leaf extends Node {
    public Leaf(String name) {
        super(name);
        this.setPath("/" + name);
        this.setParentPath("");
    }

    @Override
    protected void show() {
        System.out.println("=================当前路径:[" + this.getPath() +
                "]下文件/文件夹遍历开始===================");
        System.out.println("当前节点为叶子节点, 无子文件/文件夹");
        System.out.println("=================当前路径:[" + this.getPath() +
                "]下文件/文件夹遍历结束===================");
    }

    @Override
    public void setParentPath(String parentPath) {
        super.setParentPath(parentPath);
        this.setPath(parentPath + "/" + this.getName());
    }
}
