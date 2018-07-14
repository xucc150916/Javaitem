package com.xucc.spring2;

import com.xucc.spring2.demo.ClientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context2.xml");

        // 静态工厂创建Bean
//        ClientService clientService = (ClientService) context.getBean("clientService");
//        System.out.println(clientService);


    }
}
