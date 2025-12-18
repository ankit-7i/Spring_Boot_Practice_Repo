package com.product_inventory.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.product_inventory.model.Product;

public class ProductCrud {

    private NamedParameterJdbcTemplate template;

    public ProductCrud(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    // Insert Product
    public void addProduct(Product product) {
        String sql = "INSERT INTO product VALUES (:id, :name, :brand, :price, :stock)";
        Map<String, Object> param = new HashMap<>();
        param.put("id", product.getProductId());
        param.put("name", product.getProductName());
        param.put("brand", product.getProductBrand());
        param.put("price", product.getProductPrice());
        param.put("stock", product.getProductQuantity());
        template.update(sql, param);
    }

    // Fetch by ID
    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE product_id = :id";
        Map<String, Object> param = Map.of("id", id);
        return template.queryForObject(sql, param,
                new BeanPropertyRowMapper<>(Product.class));
    }

    
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return template.query(sql,
                new BeanPropertyRowMapper<>(Product.class));
    }

    // Update Price
    public void updatePrice(int id, double price) {
        String sql = "UPDATE product SET price = :price WHERE product_id = :id";
        Map<String, Object> param = Map.of("price", price, "id", id);
        template.update(sql, param);
    }

    // Update Stock
    public void updateStock(int id, int stock) {
        String sql = "UPDATE product SET stock_quantity = :stock WHERE product_id = :id";
        Map<String, Object> param = Map.of("stock", stock, "id", id);
        template.update(sql, param);
    }

    // Delete Product
    public void deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE product_id = :id";
        template.update(sql, Map.of("id", id));
    }
}