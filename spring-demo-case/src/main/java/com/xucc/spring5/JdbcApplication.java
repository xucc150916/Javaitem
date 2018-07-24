package com.xucc.spring5;

import com.xucc.spring5.dao.JdbcTemplateOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcApplication {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("application-jdbc.xml");
//        JdbcOperaation jdbcOperaation = context.getBean(JdbcOperaation.class);
//        // 插入
//        jdbcOperaation.insert();
//        // 查询
//        jdbcOperaation.select();

        ApplicationContext context = new ClassPathXmlApplicationContext("application-jdbc.xml");
        JdbcTemplateOperation jdbcTemplateOperation = context.getBean(JdbcTemplateOperation.class);
        jdbcTemplateOperation.select();
    }
}
