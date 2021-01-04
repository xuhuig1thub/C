package com.a.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName: PaymentFallbackService
 * @description:
 * @author: XZQ
 * @create: 2020/3/8 21:32
 **/
public class PaymentFallbackServiceImpl  {
    public String aaccpaymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall back--paymentInfo_OK";
    }

    public String aaccpaymentInfo_TimeOut(Integer id) {
        return "----PaymentFallbackService fall back--paymentInfo_TimeOut";
    }
}
