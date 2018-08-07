package com.cheng.code;

import com.cheng.security.core.validate.code.ValidateCodeGenerator;
import com.cheng.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 应用级配置验证码生成器
 *
 * @author cheng
 *         2018/8/6 23:18
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图片验证码生成代码");
        return null;
    }
}
