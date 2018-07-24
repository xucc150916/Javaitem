package com.xucc.spring4;

import com.xucc.spring4.impl.AliPayService;
import com.xucc.spring4.impl.BackService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-aop.xml");
//        PayService aliPayService = context.getBean(PayService.class);
        PayService aliPayService = (PayService) context.getBean("aliPayService");
//        PayService aliPayService = (PayService) context.getBean(PayService.class);
//        AliPayService aliPayService =  context.getBean(AliPayService.class);
        aliPayService.pay();

//        BackService backService = context.getBean(BackService.class);
//        backService.pay();
    }
}
