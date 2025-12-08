package com.example.springcoredemo_1.ContextFolder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcoredemo_1.TicketPriceCalculator;

public class Test1 {
    public static void main(String[] args) {
            ApplicationContext context =
                    new ClassPathXmlApplicationContext("bean.xml");
    
            TicketPriceCalculator calculator = (TicketPriceCalculator) context.getBean("ticketPriceCalculator");
    
            System.out.println("Ticket Price: " + calculator.calculateTicketPrice());
            ((AbstractApplicationContext) context).close();
    }
    
}
