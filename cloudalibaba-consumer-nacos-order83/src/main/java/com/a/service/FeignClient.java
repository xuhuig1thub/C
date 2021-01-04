package com.a.service;

import com.a.entities.CommonResult;
import com.a.entities.Payment;
import com.a.service.impl.FeignClientFallbackService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-29 19:58
 */
@org.springframework.cloud.openfeign.FeignClient( value = "nacos-payment-provider",fallback = FeignClientFallbackService.class )
public interface FeignClient {
    @GetMapping(value="/payment/nacos/{id}")
    String motherfucker(@PathVariable("id")int id);

    @GetMapping(value="/paymentSQL/{id}")
    CommonResult<Payment> xixi(@PathVariable("id")Long id);
}
