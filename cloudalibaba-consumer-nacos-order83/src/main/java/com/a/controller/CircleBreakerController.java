package com.a.controller;

import com.a.entities.CommonResult;
import com.a.entities.Payment;
import com.a.service.FeignClient;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sun.deploy.security.BlockedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-12-02 12:04
 */
@Slf4j
@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL="http://nacos-payment-provider";
    @Autowired
    private FeignClient feignClient;
    @Resource
    private RestTemplate restTemplate;

        @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value="any")//没有配置
//    @SentinelResource(value="fallback",fallback="aa")//fallback只负责业务异常
//    @SentinelResource(value="any",blockHandler="bbM")//blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value="fallback",fallback="aa",blockHandler = "bbM",
    exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id){
        log.info("balabala");
//        CommonResult<Payment> result=restTemplate.getForObject(
//                SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id) ;
        if(id==4){
            throw new IllegalArgumentException("IllegalArgumentException,illegal argument excep...");
        }
        CommonResult<Payment> result=feignClient.xixi(id);
        if(result.getData()==null){
            throw new NullPointerException("NullPointerException,this ID dont have corresponding record...");
        }
    return result;
    }
    public CommonResult<Payment> aa(@PathVariable Long id,Throwable throwable) {
        log.info("aa");
        Payment payment=new Payment(id,"null");
        return new CommonResult<>(444,"the content of this excep is "+ throwable.getMessage(),payment);
    }
    public CommonResult<Payment> bbM(@PathVariable Long id) {
        log.info("bbM");
        Payment payment=new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel ,:blockedException= ",payment);
    }

}
