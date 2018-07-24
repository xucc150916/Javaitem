package com.xucc.spring3;

import com.xucc.spring3.common.MyDataSource;
import com.xucc.spring3.common.MyResource;
import com.xucc.spring3.spel.SystemEnvironmentBean;
import com.xucc.spring3.spel.SystemPropertiesBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestApplicaion {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-context3.xml");
//        // 通过传入的id得到对应的bean
//        GuessNumber guessNumber = (GuessNumber) context.getBean("guessNumber");
//        System.out.println(guessNumber);
//

        // 打印系统属性
        SystemPropertiesBean systemPropertiesBean = (SystemPropertiesBean) context.getBean("systemPropertiesBean");
        System.out.println(systemPropertiesBean);

        // 打印系统环境变量
        SystemEnvironmentBean systemEnvironmentBean = (SystemEnvironmentBean) context.getBean("systemEnvironmentBean");
        System.out.println(systemEnvironmentBean);

        // myDataSource
        MyDataSource myDataSource = (MyDataSource) context.getBean("myDataSource");
        System.out.println(myDataSource);

        // MyResource
        // 字节输入流转化为字节输出流
    }
}
