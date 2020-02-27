package cn.sitedev.lsp.graph.improved;

public class Rectangle implements QuadRangle {
    private long height;
    private long width;

    public void setWidth(long width) {
        this.width = width;
    }

    @Override
    public long getWidth() {
        return width;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    @Override
    public long getHeight() {
        return height;
    }

}
