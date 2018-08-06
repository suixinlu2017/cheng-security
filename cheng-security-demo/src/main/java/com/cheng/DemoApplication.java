package com.cheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cheng
 *         2018/8/4 11:35
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security.";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}