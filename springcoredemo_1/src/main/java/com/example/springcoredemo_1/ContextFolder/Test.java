package com.example.springcoredemo_1.ContextFolder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcoredemo_1.OutfitSuggester;

public class Test {
    public static void main(String[] args) {
         ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");

        OutfitSuggester suggester = (OutfitSuggester) context.getBean("outfitSuggester");

        System.out.println(suggester.suggestOutfit());
        ((AbstractApplicationContext) context).close();

    }
    
}
