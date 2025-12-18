package com.product_inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuppressWarnings("unused")
public class Product {
    private int productId;
    private String productName;
    private String productBrand;
    private double productPrice;
    private int productQuantity;
    
}
