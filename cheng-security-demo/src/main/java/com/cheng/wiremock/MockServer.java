package com.cheng.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * WireMock 伪造REST服务
 *
 * @author cheng
 *         2018/8/5 17:13
 */
public class MockServer {
    public static void main(String[] args) throws IOException {

        // WireMock 启动端口
        configureFor(9999);
        // 清空所有配置
        removeAllMappings();

        // 模拟请求
        mock("/order/1", "01");
        mock("/order/2", "02");
    }

    private static void mock(String url, String file) throws IOException {

        // 读取资源文件
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");

        // 请求返回结果
        String content = StringUtils.join(
                FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");

        // 模拟请求
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
