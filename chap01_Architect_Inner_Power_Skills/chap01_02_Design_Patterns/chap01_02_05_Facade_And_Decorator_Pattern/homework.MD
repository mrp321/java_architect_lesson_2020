# 1、请举例3-5个使用门面模式的应用场景。

* 日志框架中通过一个接口来提供多种日志级别的输出方法

* 项目开发中分层:Controller层, Service层, Dao层

* 工具类的封装


# 2、使用装饰器模式实现一个可根据权限动态扩展功能的导航条。

## 2.1. 要求
例如：GPer社区未登录状态下的导航条

![](./homeworkMD/noLogin.png)


 
GPer社区登录状态下的导航条

![](./homeworkMD/login.png)

 多了作业、题库、成长墙的功能。
 
 ## 2.2. 实现
 
 代码[参见facade-decorator-pattern-homework](./facade-decorator-pattern-homework)
 