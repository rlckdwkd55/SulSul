package com.sulsulmarket.sulsul.payment.dao;

import com.sulsulmarket.sulsul.payment.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDao {

    @Autowired
    private PaymentMapper paymentMapper;
}
