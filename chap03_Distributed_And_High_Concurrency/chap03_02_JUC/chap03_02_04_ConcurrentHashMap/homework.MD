# 1. 请简述解决hash冲突的方法。

* 开放定址法:为产生冲突的地址,求得一个地址序列
* 链地址法:将所有哈希地址相同的记录都链接在同一链表中
* 再哈希法:产生冲突时计算另一个哈希函数地址, 直到冲突不再发生为止
* 建立一个公共溢出区:把冲突的都放在另一个地方, 不放在表里面

# 2. 什么是高低位迁移，它的好处是什么？

* ConcurrentHashMap在进行扩容时,会按原始链表顺序，过滤出来扩容后位置不变的元素（低位=0），放在一起;按原始链表顺序，过滤出来扩容后位置改变到（index+oldCap）的元素（高位=0
），放在一起.把要迁移的元素分类之后，最后在分别放到新数组对应的位置上

* 直接通过高低位分类以后，就不需要在每次扩容的时候来重新计算 hash，极大提升了效率