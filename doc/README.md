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
## 项目扩展:
#### 向 Spring 容器注册以下接口的实现，可以替换默认的处理逻辑
1. 密码加密解密策略
org.springframework.security.crypto.password.PasswordEncoder

2. 表单登录用户信息读取逻辑
org.springframework.security.core.userdetails.UserDetailsService

3. 社交登录用户信息读取逻辑
org.springframework.social.security.SocialUserDetailsService

4. Session 失效时的处理策略
org.springframework.security.web.session.InvalidSessionStrategy

5. 并发登录导致前一个 session 失效时的处理策略配置
org.springframework.security.web.session.SessionInformationExpiredStrategy

6. 退出时的处理逻辑
org.springframework.security.web.authentication.logout.LogoutSuccessHandler

7. 短信发送的处理逻辑
com.cheng.security.core.validate.code.sms.SmsCodeSender

8. 向 Spring 容器注册名为 imageValidateCodeGenerator 的 bean，可以替换默认的图片验证码生成逻辑，bean 必须实现以下接口
com.cheng.security.core.validate.code.ValidateCodeGenerator

9. 如果 Spring 容器中有下面这个接口的实现，则在社交登录无法确认用户时，用此接口的实现自动注册用户，不会跳到注册页面
org.springframework.social.connect.ConnectionSignUp