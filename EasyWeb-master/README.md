# EasyWeb

## 简介
&emsp;基于 SpringBoot、Spring Security、OAuth2.0 的前后端分离开发平台。

> 当前版本：`V2.5`，增加客户端管理，更新于2018/12/20。

## 使用技术

描述 | 框架 
:---|:---
核心框架 | Spring、Spring Boot、Spring MVC
持久层 | MyBatis、MyBatis-Plus、Druid
权限框架 | Spring Security、Security-OAuth2
前端框架 | Layui 

&emsp;如果对 security 和 oauth2 不熟悉可以在 [附件](https://gitee.com/whvse/EasyWeb/attach_files) 中下载shiro版本。


## 导入项目
1. 使用 IDEA 选择 Open 导入项目；
2. 导入数据库到MySQL中，sql 位于根目录；
3. 确认application-dev.properties 配置是否正确；
4. 浏览器访问 http://localhost:8088/，账号是`admin`、`admin`。 


## 项目结构

```text
|-main
   |-java
   |    |-com.wf.ew
   |         |-common                            // 核心模块
   |         |    |-config                       // 存放SpringBoot配置类
   |         |    |    |-MyBatisPlusConfig.java  // MyBatisPlus配置
   |         |    |    |-SwaggerConfig.java      // Swagger2配置
   |         |    |
   |         |    |-exception                    // 自定义异常,统一异常处理器
   |         |    |-oauth                        // 权限配置模块
   |         |    |-utils                        // 工具类
   |         |    |-BaseController.java          // controller基类
   |         |    |-JsonResult.java              // 结果集封装
   |         |    |-PageResult.java              // 分页结果集封装
   |         |
   |         |-oauth                             // 扩展auth框架模块
   |         |-system                            // 系统管理模块
   |         |-xxxxxx                            // 其他业务模块
   |         |
   |         |-EasyWebApplication.java           // SpringBoot启动类
   |              
   |-resources
        |-mapper                                 // mapper文件
        |    |-system
        |
        |-application.properties                 // 配置文件
```


## 项目截图

![客户端管理](https://ws1.sinaimg.cn/large/88052d6bly1fydmyl057gj20vp0haq39.jpg)

![用户管理](https://ws1.sinaimg.cn/large/88052d6bly1fydmzc42f1j210f0ha74u.jpg)

![角色管理](https://ws1.sinaimg.cn/large/88052d6bly1fydmz29i9kj20vp0hadg6.jpg)


## 相关学习资料

- [Spring Boot 从入门到进阶系列教程](http://www.spring4all.com/article/246)

- [Spring Security 从入门到进阶系列教程](http://www.spring4all.com/article/428)
- [理解OAuth 2.0 - 阮一峰](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)
- [基于Token的WEB后台认证机制](https://www.cnblogs.com/xiekeli/p/5607107.html)
- [RESTful API 设计指南](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)
- [使用Swagger2构建RESTful API](http://www.spring4all.com/article/251)
- [Swagger2 - 注解详细说明](http://www.spring4all.com/article/251)
- [IDEA SpringBoot 热部署+html修改自动刷新](https://my.oschina.net/yejunxi/blog/845752)

 :smirk: 学如逆水行舟，不进则退~~~


## 联系方式
### 欢迎加入“前后端分离技术交流群”

![群二维码](https://ws1.sinaimg.cn/large/006a7GCKgy1fstbxycj1xj305k07m75h.jpg)

### EasyWeb管理系统模板
&emsp;一个开箱即用的后台模板，使用简单，模板丰富，包含传统ifram版、spa单页面路由版，[前往查看](https://easyweb.vip)。

![EasyWeb](https://ws1.sinaimg.cn/large/88052d6bly1fydn64tcw6j20yz0jlgn4.jpg)
