package com.example.springcore_collectiondemo_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingRecommender {
    private String[] preferredCategories;
    private List<String> recentViews;
    private Map<String, String> productCategoryMap;

    public void setPreferredCategories(String[] preferredCategories) {
        this.preferredCategories = preferredCategories;
    }
    public void setRecentViews(List<String> recentViews) {
        this.recentViews = recentViews;
    }
    public void setProductCategoryMap(Map<String, String> productCategoryMap) {
        this.productCategoryMap = productCategoryMap;
    }
    


    public List<String> generateRecommendations() {
        List<String> recommendations = new ArrayList<>();

        for (String product : productCategoryMap.keySet()) {
            String category = productCategoryMap.get(product);

           
            boolean matchesPreferred = false;
            for (String preferred : preferredCategories) {
                if (preferred.equalsIgnoreCase(category)) {
                    matchesPreferred = true;
                    break;
                }
            }

            
            if (matchesPreferred && !recentViews.contains(product)) {
                recommendations.add(product);
            }
        }

        return recommendations;
    }


}
