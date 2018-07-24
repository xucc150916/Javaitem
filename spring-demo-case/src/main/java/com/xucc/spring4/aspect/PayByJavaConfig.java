package com.xucc.spring4.aspect;

import com.xucc.spring4.PayService;
import com.xucc.spring4.impl.AliPayService;
import org.springframework.context.annotation.*;

/**
 * 使用java Config 配置使用切面
 */

@EnableAspectJAutoProxy // 切面自动代理
@Configuration
@ComponentScan(basePackages = {"com.xucc.spring4.aspect"}) // 定义扫描包
public class PayByJavaConfig {

    @Bean
    public PayService payService() {
        return new AliPayService();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
    }


}
