package com.cheng.service.impl;

import com.cheng.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author cheng
 *         2018/8/4 17:51
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {

        System.out.println("greeting");
        return "hello " + name;
    }
}
