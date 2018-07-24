package com.xucc.spring2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于Java Config的配置注解方式
 */

@Configuration
public class IoCByConfigApplication {

    // 通过name找到方法
    @Bean(name = "pointMap")
    public Map<Integer, String> javaEEPoint() {
        Map<Integer, String> point = new HashMap<Integer, String>();
        point.put(1, "maven");
        point.put(2, "mybatis");
        point.put(3, "spring core");
        point.put(4, "spring mvc");
        point.put(5, "spring boot");
        return point;
    }

    // 不设置name，可以通过返回类型的class对象找到该类，前提是该返回类型只有一个
    @Bean
    public String hello() {
        return "return hello";
    }
    // hi方法也返回String，就不行了
//    @Bean
//    public String hi() {
//        return "return hi";
//    }

    public static void main(String[] args) {
        // 得到配置注解类的class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(IoCByConfigApplication.class);

         Map<Integer, String> map = (Map<Integer, String>) context.getBean("pointMap");
        System.out.println(map);

        String ret = context.getBean(String.class);
        System.out.println(ret);
    }


}
