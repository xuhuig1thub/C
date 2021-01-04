package com.a.service;

import com.a.entities.Payment;

import org.apache.ibatis.annotations.Param;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-13 9:21
 */
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id")long id) ;
}
