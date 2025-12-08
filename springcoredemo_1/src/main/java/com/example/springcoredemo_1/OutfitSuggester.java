package com.example.springcoredemo_1;
public class OutfitSuggester {
    private int temperature;

    
    public OutfitSuggester(int temperature) {
        this.temperature = temperature;
    }

    public String suggestOutfit() {
        if (temperature < 10) {
            return "Wear a heavy jacket";
        } else if (temperature >= 10 && temperature <= 20) {
            return "Wear a sweater";
        } else {
            return "Wear light clothes";
        }
    }

}
