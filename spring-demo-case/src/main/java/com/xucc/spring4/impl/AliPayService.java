package com.xucc.spring4.impl;

import com.xucc.spring4.PayService;
import org.springframework.stereotype.Service;

/**
 * 阿里支付方式
 */

@Service
public class AliPayService implements PayService {

    @Override
    public void pay() {
        // 阿里支付逻辑
        System.out.println("alipPyService");
    }
}
