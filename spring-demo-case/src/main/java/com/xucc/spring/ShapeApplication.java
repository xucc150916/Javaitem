package com.xucc.spring;


import com.xucc.spring.xml.XmlShapeCompute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShapeApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-context.xml");
        XmlShapeCompute xmlShapeCompute = (XmlShapeCompute) context.getBean("shapeComputer");
        System.out.println(xmlShapeCompute.compute(args[0]));
    }
}
