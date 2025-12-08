package com.example.springcoredemo_1;

public class TicketPriceCalculator {
    private Integer age;

    public TicketPriceCalculator(Integer age) {
        this.age = age;
    }
    public Integer calculateTicketPrice() {
        if (age < 12) {
            return 100;
        } else if (age >= 12 && age <= 60) {
            return 200;
        }
        else if(age>60){
            return 150;
        } else {
            return 0;
        }
    }
    
}
