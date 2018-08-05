package com.cheng.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author cheng
 *         2018/8/4 13:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        // 构建伪造的MVC环境
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenCreateSuccess() throws Exception {

        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":\"" + date.getTime() + "\"}";

        String result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        // 模拟执行get请求
        String result = mockMvc.perform(get("/user")
                // 请求参数
                .param("username", "cheng")
                .param("age", "21")
                .param("ageTo", "25")
                .param("xxx", "777")
                // 分页参数
//                .param("size", "7")
//                .param("page", "8")
//                .param("sort", "age.desc")
                // 发送请求是编码类型
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 返回请求后的响应码：200
                .andExpect(status().isOk())
                // 返回 json 的内容：元素数量 = 3
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {

        String result = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {

        mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 4xx 错误
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenUpdateSuccess() throws Exception {

        Date date = new Date(
                LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());

        String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null," +
                "\"birthday\":\"" + date.getTime() + "\"}";

        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void whenUploadSuccess() throws Exception {

        String result = mockMvc.perform(fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data",
                        "hello spring boot upload".getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
