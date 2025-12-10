package com.ankit.springlab.springcoredemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");

        Student s1 = context.getBean("student1", Student.class);

        System.out.println(s1);
        
    }
}
