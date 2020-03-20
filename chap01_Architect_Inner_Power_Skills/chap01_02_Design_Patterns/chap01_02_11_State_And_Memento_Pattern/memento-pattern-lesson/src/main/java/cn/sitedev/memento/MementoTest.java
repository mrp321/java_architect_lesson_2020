package cn.sitedev.memento;

public class MementoTest {
    public static void main(String[] args) {
        DraftsBox draftsBox = new DraftsBox();

        Editor editor = new Editor("这是标题1", "这是内容1", "这是图片1");

        ArticleMemento memento = editor.saveToMemento();
        draftsBox.addMemento(memento);

        System.out.println("标题:" + editor.getTitle() + "\n内容:" + editor.getContent() + "\n插图:" + editor.getImgs() + "\n 暂存成功");
        System.out.println("完整信息:" + editor);

        System.out.println("============首次修改文章============");
        editor.setTitle("这是标题2");
        editor.setContent("这是内容2");

        System.out.println("============首次修改文章完成==========");
        System.out.println("完整信息:" + editor);
        memento = editor.saveToMemento();
        draftsBox.addMemento(memento);
        System.out.println("==============保存到草稿箱===========");

        System.out.println("===========第二次修改文章===============");
        editor.setTitle("这是标题3");
        editor.setContent("这是内容3");
        System.out.println("完整信息:" + editor);
        System.out.println("==========第二次修改文章完成==============");


        System.out.println("===============第一次撤销=================");
        memento = draftsBox.getMemento();
        editor.undoFromMemento(memento);
        System.out.println("完整信息:" + editor);
        System.out.println("============第一次撤销完成================");

        System.out.println("===============第二次撤销=================");
        memento = draftsBox.getMemento();
        editor.undoFromMemento(memento);
        System.out.println("完整信息:" + editor);
        System.out.println("============第二次撤销完成================");
    }
}
