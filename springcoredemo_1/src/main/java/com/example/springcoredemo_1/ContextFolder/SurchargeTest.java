package com.example.springcoredemo_1.ContextFolder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcoredemo_1.DataUsageBillCalculator;

public class SurchargeTest {
    public static void main(String[] args) {
        ApplicationContext context =
                    new ClassPathXmlApplicationContext("bean.xml");
        DataUsageBillCalculator calc = (DataUsageBillCalculator) context.getBean("dataUsageBillCalculator");
        System.out.println("Surcharge: " + calc.calculateSurcharge());
        ((ClassPathXmlApplicationContext) context).close();
    }
    
}
