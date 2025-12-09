package com.example.springcore_collectiondemo_2.TestingFolder;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcore_collectiondemo_2.ShoppingRecommender;

public class ShoppingRecomendationTest {
   public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");

        ShoppingRecommender recommender =
                (ShoppingRecommender) context.getBean("shoppingRecommender");

        List<String> recommendations = recommender.generateRecommendations();

        System.out.println("Recommended Products: " + recommendations);
    }
}
