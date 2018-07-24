package com.xucc.spring2;

import com.xucc.spring2.assemble.Human;
import com.xucc.spring2.assemble.Person;
import com.xucc.spring2.instantiation.ClientService;
import com.xucc.spring2.instantiation.InstanceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context2.xml");

        // 构造方法实例化Bean
        ClientService clientService1 = (ClientService) context.getBean("clientService1");

        // 静态工厂创建Bean
        ClientService clientService2 = (ClientService) context.getBean("clientService2");
        System.out.println(clientService2);

        // 实例化工厂创建bean
        String msg = (String) context.getBean("instanceFactory");
        System.out.println(msg);

        // Bean对其它Bean的引用测试
        Human human = (Human) context.getBean("human");
        System.out.println(human);

        // 根据参数类型创建Bean测试
        Person person1 = (Person) context.getBean("personByType");
        System.out.println(person1);

        // 根据参数位置创建Bean测试
        Person person2 = (Person) context.getBean("personById");
        System.out.println(person2);

        // 根据参数名称创建Bean测试
        Person person3 = (Person) context.getBean("personByName");
        System.out.println(person3);
    }
}
