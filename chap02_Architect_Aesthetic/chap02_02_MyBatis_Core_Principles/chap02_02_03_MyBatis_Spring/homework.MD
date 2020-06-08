# 【作业1】
因为费用表数据量比较大，每月的数据是独立管理的，所以把年度的费用表拆分成了12张表：fee_202001- fee_202012。

费用表三个字段：id，费用金额fee_amt，费用日期fee_date。

Mapper.xml中只配置一个SQL：

```sql
select id, fee_amt，fee_date from fee where fee_date = #{ feeDate }
```


当按费用日期查询的时候，自动把逻辑表名改成对应的月份表。例如fee_date=20200510，此时需要把表名修改为`select id, fee_amt，fee_date from fee_202005 where fee_date =…`

- 1、      创建12张费用表，每张表插入一些测试数据。
- 2、      用MBG生成Mapper.xml、Bean、Mapper接口。
- 3、      编写自己的插件，实现Interceptor接口，拦截query方法（update先不用实现）。实现水平分表的逻辑。
- 4、      在mybatis-config.xml中注册插件。
- 5、      贴出运行结果。


参见[mybatis-spring-plugin-homework](mybatis-spring-plugin-homework)