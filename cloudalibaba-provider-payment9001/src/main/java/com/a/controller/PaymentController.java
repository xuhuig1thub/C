package com.a.controller;

import com.a.entities.CommonResult;
import com.a.entities.Payment;
import com.a.myhandler.CustomerBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-29 19:28
 */
@Slf4j
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos register, serverport=" + serverPort + "\t id:" + id;
    }

    @GetMapping("/testA")
    public  String fsfs(){
        return "------testA";
    }

    @GetMapping("/testB")
    public String fdsfsd(){
        return "------testB";
    }

    @GetMapping("/testC")
    public String fds(){
        log.info(Thread.currentThread().getName()+"\t "+"...testC");
        return "------testC";
    }
    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD RT");
        return "------testD";
    }
    @GetMapping("/testE")
    public String testE(){
        log.info("testE exception");
        int age= 10/0;
        return "------testE";
    }
    @GetMapping("/testF")
    public String testF(){
        return "------testF";
    }
    @GetMapping("/testG")
    public String testG(){
        return "------testG";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "abc",blockHandler = "deal_testHotKey")
    public String testHotkey(@RequestParam(value="p1",required=false)String p1,
                             @RequestParam(value="p2",required = false)String p2){
        return "-----testHotKey"+p1+"----"+p2;
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "----deal_hotkey";
    }

    @GetMapping("/byResource")
    @SentinelResource(value="byResource",blockHandler = "handleException")
    public CommonResult fsdfds()
    {
        return new CommonResult(200,
                "limit flow testing by resource name",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,
                exception.getClass().getCanonicalName()+"\t service is inavailable");
    }
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value="byUrl")
    public CommonResult byUrl(){
        return  new CommonResult(200,"client customized definition",new Payment(2020L,"serial003"));
    }
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value="customerBlockH",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler ="handlerException2")
    public CommonResult dfsfdsf(){
        return  new CommonResult(200,"client customized definition",new Payment(2020L,"serial003"));
    }


    public static HashMap<Long,Payment> hashMap=new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"12a8c"));
        hashMap.put(2L,new Payment(2L,"bba8c"));
        hashMap.put(3L,new Payment(3L,"6ua8c"));
    }

    @GetMapping(value="/paymentSQL/{id}")
    public CommonResult<Payment> fds(@PathVariable("id") Long id)  {
        Payment payment=hashMap.get(id);
        CommonResult<Payment> result=new CommonResult<>(200,
                "from mysql,serverPort:"+serverPort,payment);
        return result;
    }
}

