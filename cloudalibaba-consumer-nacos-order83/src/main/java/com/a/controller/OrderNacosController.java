package com.a.controller;

import com.a.service.FeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: OrderNacosController
 * @description:
 * @author: XZQ
 * @create: 2020/3/11 15:36
 **/
@RestController("OrderNacos1")
@RequestMapping("/consumer")
public class OrderNacosController {
    @Autowired
    private FeignClient feignClient;
//    @Autowired
//    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
//        return restTemplate.getForObject(serverUrl + "/payment/nacos/" + id, String.class);
        return feignClient.motherfucker(id);
    }



}
