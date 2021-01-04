package com.a.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

/**
 * @InterfaceName: PaymentHystrixService
 * @description:
 * @author: XZQ
 * @create: 2020/3/8 17:00
 **/
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentHystrixService {


    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    String aaccpaymentInfo_OK(@PathVariable("id") Integer id);

    /**
     * 超时访问
     *
     * @param id
     * @return
     */

    @GetMapping("/payment/hystrix/timeout/{id}")
    String aaccpaymentInfo_TimeOut(@PathVariable("id") Integer id);


    @GetMapping("/payment/tttzipkin")
    String fds();

}