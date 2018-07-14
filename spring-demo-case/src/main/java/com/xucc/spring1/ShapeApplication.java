package com.xucc.spring1;


import com.xucc.spring1.xml.XmlShapeCompute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShapeApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-context1.xml");
        // 通过传入的id得到对应的bean
        XmlShapeCompute xmlShapeCompute = (XmlShapeCompute) context.getBean("shapeComputer");
        System.out.println(xmlShapeCompute.compute("circular").computerArea());
    }
}
