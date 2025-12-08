package com.example.springcoredemo_1;

public class BillCalculator {
    private Integer units;

    public BillCalculator(Integer units) {
        this.units = units;
    }
    
    public Double calculateBillAmount() {
        double bill;

        if (units <= 100) {
            bill = units * 1.5;
        } else if (units <= 300) {
            bill = (100 * 1.5) + (units - 100) * 2.5;
        } else {
            bill = (100 * 1.5) + (200 * 2.5) + (units - 300) * 4;
        }

        return bill;
    }
}