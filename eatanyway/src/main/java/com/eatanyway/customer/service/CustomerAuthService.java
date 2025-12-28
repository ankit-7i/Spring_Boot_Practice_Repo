package com.eatanyway.customer.service;

import com.eatanyway.customer.dao.CustomerAuthDao;
import com.eatanyway.customer.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthService {

    private final CustomerAuthDao customerDao;

    public CustomerAuthService(CustomerAuthDao customerDao) {
        this.customerDao = customerDao;
    }

    public String signup(Customer customer) {

        if (customer.getName() == null || customer.getName().isBlank()) {
            return "Name is required";
        }

        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            return "Invalid email";
        }

        if (customer.getPassword() == null || customer.getPassword().length() < 6) {
            return "Password must be at least 6 characters";
        }

        if (customerDao.findByEmail(customer.getEmail()) != null) {
            return "Email already registered";
        }

        customerDao.save(customer);
        return "SUCCESS";
    }

    public Customer login(String email, String password) {

        Customer customer = customerDao.findByEmail(email);

        if (customer == null) {
            return null;
        }

        if (!customer.getPassword().equals(password)) {
            return null;
        }

        return customer;
    }
}

