package cn.sitedev.auth.chainbuilder;

import cn.sitedev.auth.Member;

public abstract class Handler<T> {
    protected Handler chain;

    private void next(Handler handler) {
        this.chain = handler;
    }

    public abstract void doHandle(Member member);

    public static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;

        public Builder<T> addHandler(Handler<T> handler) {
            if (this.head == null) {
                // 节点为空
                this.head = this.tail = handler;
                return this;
            }
            // 往尾部添加节点
            this.tail.next(handler);
            this.tail = handler;
            return this;
        }

        public Handler<T> build() {
            return this.head;
        }
    }

}
