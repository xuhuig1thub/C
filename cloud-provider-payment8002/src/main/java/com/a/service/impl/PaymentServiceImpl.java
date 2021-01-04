package com.a.service.impl;

import com.a.dao.PaymentDao;
import com.a.entities.Payment;
import com.a.service.PaymentService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-13 9:22
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    public int create(Payment payment){
        return paymentDao.create(payment);
    }
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
