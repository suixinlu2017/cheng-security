package com.cheng.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @author cheng
 *         2018/8/5 15:41
 */
@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order1")
    public String order1() throws InterruptedException {

        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");

        return "success";
    }

    @RequestMapping("/order2")
    public Callable<String> order2() {

        logger.info("主线程开始");

        Callable<String> result = () -> {
            logger.info("副线程开始");
            Thread.sleep(1000);
            logger.info("副线程返回");
            return "success";
        };

        logger.info("主线程返回");

        return result;
    }

    @RequestMapping("/order3")
    public DeferredResult<String> order3() {

        logger.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);

        logger.info("主线程返回");
        return result;
    }
}
