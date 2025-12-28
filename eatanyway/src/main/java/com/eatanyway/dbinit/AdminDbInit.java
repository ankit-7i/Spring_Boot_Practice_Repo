package com.eatanyway.dbinit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AdminDbInit {

    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3307/eatanyway_admin";
        String user = "root";
        String pass = "Ankit07!";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS admin_user (
                    admin_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(50),
                    email VARCHAR(100) UNIQUE,
                    password VARCHAR(100)
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS category (
                    category_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(50)
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS menu_item (
                    item_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100),
                    price DECIMAL(8,2),
                    category_id INT
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS pricing_rules (
                    rule_id INT PRIMARY KEY AUTO_INCREMENT,
                    new_year_discount DECIMAL(5,2),
                    handling_charge DECIMAL(5,2),
                    surge_charge DECIMAL(5,2)
                )
            """);

            System.out.println("Admin DB tables created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

