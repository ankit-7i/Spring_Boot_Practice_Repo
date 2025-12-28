package com.eatanyway.admin.dao;

import com.eatanyway.admin.model.AdminUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminAuthDao {

    private final JdbcTemplate jdbcTemplate;

    public AdminAuthDao(JdbcTemplate adminJdbcTemplate) {
        this.jdbcTemplate = adminJdbcTemplate;
    }

    public int save(AdminUser admin) {
        return jdbcTemplate.update(
            "INSERT INTO admin_user(name, email, password) VALUES(?,?,?)",
            admin.getName(),
            admin.getEmail(),
            admin.getPassword()
        );
    }

    public AdminUser findByEmail(String email) {
        return jdbcTemplate.query(
            "SELECT * FROM admin_user WHERE email=?",
            rs -> {
                if (rs.next()) {
                    AdminUser a = new AdminUser();
                    a.setAdminId(rs.getInt("admin_id"));
                    a.setName(rs.getString("name"));
                    a.setEmail(rs.getString("email"));
                    a.setPassword(rs.getString("password"));
                    return a;
                }
                return null;
            },
            email
        );
    }
}

