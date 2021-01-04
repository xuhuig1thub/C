package com.a.controller;

import com.a.entities.CommonResult;
import com.a.entities.Payment;
import com.a.service.PaymentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-13 9:25
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverport;

    @PostMapping(value="/payment/create")
    public CommonResult fdd(@RequestBody Payment payment){
        log.info("8002-create");
        int result =paymentService.create(payment);
        log.info("result:"+result);
        if(result>0){
            return new CommonResult(200,"succ,serverPort:"+ serverport,result);
        }else{
            return new CommonResult(444,"failed",null);
        }
    }
    @GetMapping(value="/payment/get/{id}")
    public CommonResult ddd(@PathVariable("id") Long id){
        log.info("8002-getPaymentById");
       Payment payment=paymentService.getPaymentById(id);
        log.info("result:"+payment);
        if(payment!=null){
            return new CommonResult(200,"succ,serverPort:"+ serverport,payment);
        }else{
            return new CommonResult(444,"failed",null);
        }
    }
    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverport;
    }


}
