package com.xucc.spring2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基于Java Config的配置注解方式
 */

@Configuration
public class IoCJavaConfigApplication {
    /**
     * 用于演示的内部类
     */
    public static class Student {
        private int id;
        private String name;

        // getter,setter
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Bean
    public String hello() {
        return "hello";
    }

    @Bean(value = "zhangsanStudent")
    public Student zhangsan() {
        Student student = new Student();
        student.setId(1);
        student.setName("zhangsan");
        return student;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(IoCJavaConfigApplication.class);
        String res1 = (String) context.getBean("hello");
        System.out.println(res1);

        Student student = (Student) context.getBean("zhangsanStudent");
        System.out.println(student);
    }
}
