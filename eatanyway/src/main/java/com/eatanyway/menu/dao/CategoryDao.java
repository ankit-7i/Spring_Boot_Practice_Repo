package com.eatanyway.menu.dao;

import com.eatanyway.menu.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public CategoryDao(JdbcTemplate adminJdbcTemplate) {
        this.jdbcTemplate = adminJdbcTemplate;
    }

    public void addCategory(String name) {
        jdbcTemplate.update(
            "INSERT INTO category(name) VALUES (?)",
            name
        );
    }

    public List<Category> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM category",
            (rs, rowNum) -> {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setName(rs.getString("name"));
                return c;
            }
        );
    }
}

