package com.cheng.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 生成验证码的请求处理器
 *
 * @author cheng
 *         2018/8/6 19:38
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @GetMapping("/code/{type}")
    public void createCode(@PathVariable String type,HttpServletRequest request,HttpServletResponse response) throws Exception {
        validateCodeProcessors.get(type+"CodeProcessor").create(new ServletWebRequest(request,response));
    }
}
