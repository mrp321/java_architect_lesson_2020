package cn.sitedev.lsp.graph;

public class LiskovSubstitutionTest {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(20);
        rectangle.setHeight(10);
        resize(rectangle);
        System.out.println("======================");
        Square square = new Square();
        square.setLength(10);
        resize(square);
    }

    public static void resize(Rectangle rectangle) {
        while (rectangle.getWidth() >= rectangle.getHeight()) {
            rectangle.setHeight(rectangle.getHeight() + 1);
            System.out.println("width:" + rectangle.getWidth() + ", height:" + rectangle.getHeight());
        }
        System.out.println("resize end, width:" + rectangle.getWidth() + ", height:" + rectangle.getHeight());
    }
}
