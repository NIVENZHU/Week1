# Week1
This task is mainly about the creation of database in mysql and implement method to perform operations on data through mybatis plus


文件结构：
1. 项目中所有的代码都在src目录中，pom.xml中导入依赖
2. src/main/java代码区
   2.1 entity目录中，存放了数据库对应的实例类，通过实例来进行数据交互
   2.2 mapper目录中存放的是映射接口
   2.3 service中分别存放了用户，广告，新闻，与评论的数据增删改查的方法接口以及实现类，以及业务逻辑异常类
   2.4 utils中包含了工具类，现阶段存在md5密码加密的工具方法
3. src/main/resources
   3.1 mapper中存放了sql的映射文件
   3.2 SQL中存放了sql文件，用于数据库的创建
   3.3 applicationContext.xml---Spring配置文件
   3.4 logback.xml 日志
   3.5 mybatis-config.xml---mybatis配置文件
4. src/main/webapp在此次任务中并未使用， 其中web.xml用于配置dispatcherServlet以及解决post请求中的中文乱码问题
5. src/test中存放了每个service方法对应的测试方法，结构一一对应
