package cn.sitedev.composite;

import lombok.Data;

@Data
public abstract class Node {
    private String parentPath;
    private String name;
    private String path;

    public Node(String name) {
        this.name = name;
    }

    protected void addChild(Node child) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    protected void show() {
        throw new UnsupportedOperationException("不支持展示操作");
    }

}
