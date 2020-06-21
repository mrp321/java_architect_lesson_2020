# 1. 如果一个子类实现了序列化，父类没有实现，那么父类中的成员变量能否被序列化？

* 不可以

# 2. transient是干嘛的？有什么方法能够绕过transient的机制。这个实现机制的原理是什么？

* 该关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中

* 可以通过在实现序列化接口的类中添加私有的readObject()和writeObject()方法来绕过transient机制

* 在ObjectInputStream的readObject()中调用了实现了序列化接口的类中的readObject(ObjectInputStream)方法;ObjectOutputStream的writeObject
()中调用了实现了序列化接口的类中的writeObject(ObjectOutputStream)方法


# 3. serializable的安全性如何保证？

* Java 的序列化机制是通过判断类的 serialVersionUID 来验证版本一致性的。在进行反序列化时，JVM 会把传来的字节流中的 serialVersionUID 与本地相应实体类的 serialVersionUID
 进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常，即是 InvalidCastException。

# 4. 有没有了解过protobuf，它的序列化实现原理是什么？

* protobuf 采用 T-L-V 作为存储方式, 而且用到了两种压缩算法: varint和zigzag


# 5. serialVersionUID的 作 用 是 什 么 ？ 如 果 我 不 设 置serialVersionUID,有没有问题？

* 通过判断类的 serialVersionUID 来验证版本一致性的
* 没有问题. 如果没有为指定的class 配置 serialVersionUID，那么 java 编译器会自动给这个 class 进行一个摘要算法，类似于指纹算法，只要这个文件有任何改动，得到的 UID就会截然不同的，可以保证在这么多类中，这个编号是唯一的。