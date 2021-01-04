package com.a.controller;

import com.a.entities.CommonResult;
import com.a.entities.Payment;
import com.a.service.PaymentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-13 9:25
 */
@RestController
@Slf4j
@ApiModel(value="apiModel's value")
public class PaymentController {
    @ApiModelProperty(value="@ApiModelProperty's value")
    @Resource
    private PaymentService paymentService;
    @ApiModelProperty(value="服务器端口",example="8080 ,8001,8002")
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;
    @ApiOperation(value="valuefdd",notes="notes",tags="tags",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="payment-n",value="支付",required = true,dataType = "com.a.entities.Payment.class")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="succ",response=CommonResult.class)
    })
    @PostMapping(value="/payment/create")
    public CommonResult fdd(@RequestBody Payment payment){
        log.info("8001-create");
        int result =paymentService.create(payment);
        log.info("result:"+result);
        if(result>0){
            return new CommonResult(200,"succ,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(444,"failed",null);
        }
    }
    @GetMapping(value="/payment/get/{id}")
    public CommonResult fdsfdsfds(@PathVariable("id") Long id){
        log.info("8001-getPaymentById");
       Payment payment=paymentService.getPaymentById(id);
        log.info("result:"+payment);
        if(payment!=null){
            return new CommonResult(200,"succ,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(444,"failed",null);
        }
    }
    @GetMapping(value="/payment/discovery")
    public Object d(){
        List<String> services=discoveryClient.getServices();
        for(String e:services){
            log.info("e:"+e);
        }
        List<ServiceInstance> instances= discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
            instances.forEach(i->System.out.println(i.getServiceId()+"\t"+i.getHost()+"\t"+i.getPort()+"\t"+i.getUri()));
    return instances;
    }

    @GetMapping("/payment/feign/timeout")
    public String fds(){
        try{
            TimeUnit.SECONDS.sleep(3);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
