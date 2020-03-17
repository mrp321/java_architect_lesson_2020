package cn.sitedev.course;

import java.util.List;

public class IteratorImpl<E> implements Iterator<E> {
    private List<E> list;
    private int cursor;
    private E element;

    public IteratorImpl(List<E> list) {
        this.list = list;
    }

    @Override
    public E next() {
        System.out.println("当前位置:" + cursor);
        this.element = this.list.get(this.cursor);
        this.cursor++;
        return element;
    }

    @Override
    public boolean hasNext() {
        if (this.cursor > this.list.size() - 1) {
            return false;
        }
        return true;
    }
}
