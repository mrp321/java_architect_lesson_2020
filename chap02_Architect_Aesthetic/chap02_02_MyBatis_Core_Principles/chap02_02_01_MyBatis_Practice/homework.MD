# 1、为什么要用MyBatis？MyBatis解决了什么问题？

MyBatis对jdbc的操作数据库的过程进行封装，使开发者只需要关注 SQL 本身，而不需要花费精力去处理例如注册驱动、创建connection、创建statement、手动设置参数、结果集检索等jdbc繁杂的过程代码。


# 2、一个商户，在登记的时候需要注册它的经营范围。比如1手机，2电脑，3相机，4平板，在界面上是一个复选框(checkbox)。  在数据库保存的是用逗号分隔的字符串，例如“1,3,4”，而返回给程序的时候是整形数组`List<Integer>` {1,3,4}。 实现一个TypeHandler，可以把`List<Integer>`转换成数据库的varchar。把数据库的varchar转换成`List<Integer>`。 需要创建一张表、创建POJO、Mapper、xml映射器。

参见[mybatis-homework](mybatis-homework)