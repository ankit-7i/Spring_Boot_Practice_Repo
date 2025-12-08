package com.example.springcoredemo_1.ContextFolder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcoredemo_1.IncomeTaxCalculator;

public class IncomeTaxTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IncomeTaxCalculator taxCalculator = (IncomeTaxCalculator) context.getBean("incomeTaxCalculator");
        System.out.println("Income Tax: " + taxCalculator.calculateTax());
        ((ClassPathXmlApplicationContext) context).close();
    }
    
}
