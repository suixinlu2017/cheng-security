## 项目使用教程
1. 引入依赖(pom.xml)
    - 浏览器模块:
        ```xml
        <dependency>
            <groupId>com.cheng.security</groupId>
            <artifactId>cheng-security-browser</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
        ```
2. 配置详情:(参见 [application-example.properties](application-example.properties))

3. 增加 UserDetailsService 接口实现

4. 如果需要记住我功能，需要创建数据库表(参见 [db.sql](db.sql))

5. 如果需要社交登录功能，需要以下额外的步骤:
   - 配置 appId 和 appSecret;
   - 创建并配置用户注册页面，并实现注册服务(需要配置访问权限)，注意在服务中要调用 ProviderSignInUtils 的 doPostSignUp 方法;
   - 添加SocialUserDetailsService接口实现;
   - 创建社交登录用的表 (参见 [db.sql](db.sql));