package com.eatanyway.customer.dao;

import com.eatanyway.customer.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerAuthDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerAuthDao(JdbcTemplate customerJdbcTemplate) {
        this.jdbcTemplate = customerJdbcTemplate;
    }

    public int save(Customer customer) {
        return jdbcTemplate.update(
            "INSERT INTO customer(name, email, password) VALUES (?,?,?)",
            customer.getName(),
            customer.getEmail(),
            customer.getPassword()
        );
    }

    public Customer findByEmail(String email) {
        return jdbcTemplate.query(
            "SELECT * FROM customer WHERE email=?",
            rs -> {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.setCustomerId(rs.getInt("customer_id"));
                    c.setName(rs.getString("name"));
                    c.setEmail(rs.getString("email"));
                    c.setPassword(rs.getString("password"));
                    return c;
                }
                return null;
            },
            email
        );
    }
}


