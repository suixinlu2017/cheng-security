package com.cheng.security.core.properties;

/**
 * 图片验证码配置项
 *
 * @author cheng
 *         2018/8/6 22:32
 */
public class ImageCodeProperties {

    /**
     * 图片宽
     */
    private int width = 67;

    /**
     * 图片高
     */
    private int height = 23;

    private int length = 4;

    private int expire = 60;

    private String url;

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
