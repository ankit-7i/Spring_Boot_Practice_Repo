package com.product_inventory.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateProductTable {

    
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "Ankit07%";

    public static void main(String[] args) {

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            
            Statement stmt = con.createStatement();

            
            String createDb = "CREATE DATABASE IF NOT EXISTS springcoredemo";
            stmt.executeUpdate(createDb);

            
            stmt.execute("USE springcoredemo");

            
            String createTable =
                    "CREATE TABLE IF NOT EXISTS product (" +
                    "product_id INT PRIMARY KEY," +
                    "product_name VARCHAR(100) NOT NULL," +
                    "brand VARCHAR(50) NOT NULL," +
                    "price DOUBLE(10,2) NOT NULL," +
                    "stock_quantity INT NOT NULL" +
                    ")";

            stmt.executeUpdate(createTable);

            System.out.println("Database and Product table created successfully");

           
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}