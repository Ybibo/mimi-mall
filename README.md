# 米米商城-SSM项目

## 搭建ssm项目步骤总结
1. 新增Maven工程
2. 修改pom.xml文件
3. 添加ssm框架的所有依赖
4. 创建jdbc.properties到resource目录
5. 创建mybatis-config.xml,进行分页插件的配置
6. 创建spring-mapper.xml文件,进行数据访问层配置
7. 创建spring-service.xml文件,进行业务逻辑层的配置
8. 创建spring-mvc.xml,配置springMVC的框架 
9. 使用逆向工程生成pojo和mapper文件(MyBatisGeneratorTools)
10. 开发业务逻辑层，实现登陆判断
11. 开发控制器AdminAction,完成登陆处理
12. 改造页面,发送登陆请求，验证登陆
---
## 所用技术以及工具

* 后端：Spring、SpringMVC、Mybatis

* 前端：jsp、AJAX异步刷新技术

* 数据库：MySQL


