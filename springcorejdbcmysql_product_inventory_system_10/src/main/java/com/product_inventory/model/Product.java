package com.product_inventory.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Product {
    private int productId;
    private String productName;
    private String productBrand;
    private double productPrice;
    private int productQuantity;
    
}
