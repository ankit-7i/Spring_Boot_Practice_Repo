package com.eatanyway.admin.service;

import com.eatanyway.admin.dao.AdminAuthDao;
import com.eatanyway.admin.model.AdminUser;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    private final AdminAuthDao adminDao;

    public AdminAuthService(AdminAuthDao adminDao) {
        this.adminDao = adminDao;
    }

    public String signup(AdminUser admin) {

        if (admin.getName() == null || admin.getName().isBlank()) {
            return "Name is required";
        }

        if (admin.getEmail() == null || !admin.getEmail().contains("@")) {
            return "Invalid email";
        }

        if (admin.getPassword() == null || admin.getPassword().length() < 6) {
            return "Password must be at least 6 characters";
        }

        if (adminDao.findByEmail(admin.getEmail()) != null) {
            return "Email already registered";
        }

        adminDao.save(admin);
        return "SUCCESS";
    }

    public AdminUser login(String email, String password) {

        AdminUser admin = adminDao.findByEmail(email);

        if (admin == null) {
            return null;
        }

        if (!admin.getPassword().equals(password)) {
            return null;
        }

        return admin;
    }
}

