package com.a.controller;

import com.a.entities.CommonResult;
import com.a.service.PaymentFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OrderFeignClientController
 * @description:
 * @author: XZQ
 * @create: 2020/3/8 15:28
 **/
@RestController
@RequestMapping("/consumer")
public class OrderFeignClientController {
    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @GetMapping(value = "/payment/getfeign/{id}")
    public CommonResult b(@PathVariable("id") Long id) {
        return paymentFeignClient.bb(id);
    }

    /**
     * 模拟feign超时
     *
     * @return
     */
    @GetMapping(value = "/payment/feign/imeout")
    public String paymentFeignTimeout() {
        // openfeign-ribbon, 客户端一般默认等待1秒钟
        return paymentFeignClient.paymentFeignTimeout();
    }


}
