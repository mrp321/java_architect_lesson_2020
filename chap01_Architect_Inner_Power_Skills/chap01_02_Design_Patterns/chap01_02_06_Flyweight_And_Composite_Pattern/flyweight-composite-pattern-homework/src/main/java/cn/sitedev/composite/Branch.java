package cn.sitedev.composite;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Branch extends Node {
    private List<Node> childPathList = new ArrayList<>();

    public Branch(String name) {
        super(name);
        this.setPath("/" + name);
        this.setParentPath("");
    }

    @Override
    protected void addChild(Node child) {
        child.setParentPath(this.getPath());
        childPathList.add(child);
        if (child instanceof Branch) {
            List<Node> childPathListOfChild = ((Branch) child).getChildPathList();
            Iterator<Node> iterator = childPathListOfChild.iterator();
            while (iterator.hasNext()) {
                Node childOfChild = iterator.next();
                String childPathOfChild = childOfChild.getPath();
                String newChildPathOfChild = this.getPath() + childPathOfChild;
                childOfChild.setPath(newChildPathOfChild);
            }
        }
    }

    @Override
    public void setParentPath(String parentPath) {
        super.setParentPath(parentPath);
        this.setPath(parentPath + "/" + this.getName());
    }

    @Override
    protected void show() {
        System.out.println("=================当前路径:[" + this.getPath() +
                "]下文件/文件夹遍历开始===================");
        for (Node node : childPathList) {
            System.out.println(node.getPath());
        }
        System.out.println("=================当前路径:[" + this.getPath() +
                "]下文件/文件夹遍历结束===================");
    }
}
