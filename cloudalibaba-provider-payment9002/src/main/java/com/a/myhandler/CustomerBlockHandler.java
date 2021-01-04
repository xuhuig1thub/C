package com.a.myhandler;

import com.a.entities.CommonResult;
import com.a.entities.Payment;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-12-02 10:56
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException blockException){
        return new CommonResult(444,"client customized ,global handlerException-----1",new Payment(2020L,"serial003"));
    }

    public static CommonResult handlerException2(BlockException blockException){
        return new CommonResult(444,"client customized ,global handlerException-----2",new Payment(2020L,"serial003"));
    }
}
