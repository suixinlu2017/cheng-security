package com.cheng.security.core.properties;

/**
 * 验证码配置
 *
 * @author cheng
 *         2018/8/6 22:34
 */
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
