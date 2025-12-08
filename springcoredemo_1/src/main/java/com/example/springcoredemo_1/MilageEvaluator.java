package com.example.springcoredemo_1;

public class MilageEvaluator {
    private Double distance;   
    private Double fuelUsed;   

    
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setFuelUsed(Double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    public double calculateMileage() {
        return distance / fuelUsed;
    }

    public String getMileageStatus() {
        Double mileage = calculateMileage();

        if (mileage < 10) {
            return "Poor";
        } else if (mileage <= 18) {
            return "Average";
        } else {
            return "Excellent";
        }
    }
}
