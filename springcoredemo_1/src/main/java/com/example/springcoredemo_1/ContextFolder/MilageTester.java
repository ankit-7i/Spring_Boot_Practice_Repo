package com.example.springcoredemo_1.ContextFolder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcoredemo_1.MilageEvaluator;

public class MilageTester {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        MilageEvaluator evaluator = (MilageEvaluator) context.getBean("milageEvaluator");

        Double mileage = evaluator.calculateMileage();
        String status = evaluator.getMileageStatus();

        System.out.println("Mileage: " + mileage + " km/l");
        System.out.println("Category: " + status);
    }

}
