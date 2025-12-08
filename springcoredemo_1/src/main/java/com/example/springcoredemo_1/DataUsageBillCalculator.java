package com.example.springcoredemo_1;

public class DataUsageBillCalculator {
    private Double dataUsedInGB;
    public DataUsageBillCalculator(Double dataUsedInGB) {
        this.dataUsedInGB = dataUsedInGB;
    }
    public Double calculateSurcharge() {
    double surcharge = 0.0;

    if (dataUsedInGB <= 2) {
        surcharge = 0.0;
    } 
    else if (dataUsedInGB <= 10) {
        surcharge = (dataUsedInGB - 2) * 20.0;
    } 
    else {
        surcharge = (8 * 20.0) + (dataUsedInGB - 10) * 50.0;
    }

    return surcharge;
}

}
