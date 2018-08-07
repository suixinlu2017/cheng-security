package com.cheng.security.core.properties;

/**
 * 图片验证码配置项
 *
 * @author cheng
 *         2018/8/6 22:32
 */
public class ImageCodeProperties extends SmsCodeProperties {

    /**
     * 图片宽
     */
    private int width = 67;

    /**
     * 图片高
     */
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
