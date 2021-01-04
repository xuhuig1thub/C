package com.a.service.impl;

import com.a.entities.CommonResult;
import com.a.entities.Payment;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-12-02 13:14
 */
@Component
public class FeignClientFallbackService {

    String motherfucker(@PathVariable("id")int id){
        return "motherfucker";
    }

    CommonResult<Payment> xixi(@PathVariable("id")Long id){
        return new CommonResult<>(44444,"service is placed at a lower position," +
                "--FeignClientFallbackService",new Payment(id,"errorSerial"));
    }
}
