package com.eatanyway.menu.dao;

import com.eatanyway.menu.model.MenuItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuItemDao {

    private final JdbcTemplate jdbcTemplate;

    public MenuItemDao(JdbcTemplate adminJdbcTemplate) {
        this.jdbcTemplate = adminJdbcTemplate;
    }

    public void addMenuItem(MenuItem item) {
        jdbcTemplate.update(
            "INSERT INTO menu_item(name, price, category_id) VALUES (?,?,?)",
            item.getName(),
            item.getPrice(),
            item.getCategoryId()
        );
    }

    public List<MenuItem> findByCategory(int categoryId) {
        return jdbcTemplate.query(
            "SELECT * FROM menu_item WHERE category_id=?",
            (rs, rowNum) -> {
                MenuItem m = new MenuItem();
                m.setItemId(rs.getInt("item_id"));
                m.setName(rs.getString("name"));
                m.setPrice(rs.getDouble("price"));
                m.setCategoryId(rs.getInt("category_id"));
                return m;
            },
            categoryId
        );
    }
}

