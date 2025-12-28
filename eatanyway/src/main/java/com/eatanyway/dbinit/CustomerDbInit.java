package com.eatanyway.dbinit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CustomerDbInit {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/eatanyway_customer";
        String user = "root";
        String pass = "Ankit07%";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS customer (
                    customer_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(50),
                    email VARCHAR(100) UNIQUE,
                    password VARCHAR(100)
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    order_id INT PRIMARY KEY AUTO_INCREMENT,
                    customer_id INT,
                    total DECIMAL(10,2),
                    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS payment (
                    payment_id INT PRIMARY KEY AUTO_INCREMENT,
                    order_id INT,
                    method VARCHAR(20),
                    amount DECIMAL(10,2),
                    status VARCHAR(20)
                )
            """);

            System.out.println("Customer DB tables created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

