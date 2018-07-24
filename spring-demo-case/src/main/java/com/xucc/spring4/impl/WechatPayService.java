package com.xucc.spring4.impl;

import com.xucc.spring4.PayService;
import org.springframework.stereotype.Service;

@Service
public class WechatPayService implements PayService {
    @Override
    public void pay() {
        // 微信支付逻辑
        System.out.println("wechatPayService");
    }
}
