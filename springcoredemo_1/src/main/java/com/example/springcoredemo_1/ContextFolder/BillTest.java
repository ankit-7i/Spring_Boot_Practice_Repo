package com.example.springcoredemo_1.ContextFolder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcoredemo_1.BillCalculator;

public class BillTest {
    public static void main(String[] args) {
        ApplicationContext context =
                    new ClassPathXmlApplicationContext("bean.xml");

    BillCalculator calculator = (BillCalculator) context.getBean("billCalculator");
    System.out.println("Bill Amount: " + calculator.calculateBillAmount());
    ((ClassPathXmlApplicationContext) context).close();
    }
    
}
