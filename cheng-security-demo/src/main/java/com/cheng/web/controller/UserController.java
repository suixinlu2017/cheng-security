package com.cheng.web.controller;

import com.cheng.dto.User;
import com.cheng.dto.UserQueryCondition;
import com.cheng.security.app.social.AppSignUpUtils;
import com.cheng.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cheng
 *         2018/8/4 13:39
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 注册用户
     *
     * @param user
     * @param request
     */
    @PostMapping("/register")
    public void register(User user, HttpServletRequest request) {

        // 不管是注册用户还是绑定用户都会拿到一个用户唯一标识
        String userId = user.getUsername();

        // 浏览器环境下用户注册
//        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
        // app环境下用户注册
        appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }

    /**
     * 获取当前用户认证信息
     *
     * @return
     */
    @GetMapping("/me")
    public Object getCurrentUser(/*@AuthenticationPrincipal UserDetails userDetails*/
            Authentication user, HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        Claims claims = Jwts.parser()
                .setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody();

        String company = (String) claims.get("company");
        System.out.println(company);

        return user;
    }

    /**
     * 创建用户
     *
     * @return
     */
    @PostMapping
    @ApiOperation(value = "创建用户")
    public User create(@Valid @RequestBody User user) {

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 2, size = 8, sort = "username.asc") Pageable pageable
            /*@RequestParam(name = "username", required = false, defaultValue = "zy") String nickname*/) {

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());

        return users;
    }

    /**
     * 获取用户详情
     *
     * @return
     */
    @RequestMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户id") @PathVariable(name = "id", required = false) String id) {

//        throw new UserNotExistException(id);
//        throw new RuntimeException("user not exist");
        System.out.println("进入 getInfo 服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    /**
     * 修改用户
     *
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public User update(@PathVariable String id, @Valid @RequestBody User user, BindingResult errors) {

        // BindingResult 用于与注解 @Valid 配合使用，记录错误信息
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
