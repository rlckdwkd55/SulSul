package com.sulsulmarket.sulsul.payment.service;

import com.sulsulmarket.sulsul.payment.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;
}
