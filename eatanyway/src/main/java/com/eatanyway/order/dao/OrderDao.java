package com.eatanyway.order.dao;

import com.eatanyway.order.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderDao(JdbcTemplate customerJdbcTemplate) {
        this.jdbcTemplate = customerJdbcTemplate;
    }

    public int saveOrder(Order order) {

        jdbcTemplate.update(
            "INSERT INTO orders(customer_id, total) VALUES (?,?)",
            order.getCustomerId(),
            order.getTotal()
        );

        return jdbcTemplate.queryForObject(
            "SELECT LAST_INSERT_ID()",
            Integer.class
        );
    }
}

