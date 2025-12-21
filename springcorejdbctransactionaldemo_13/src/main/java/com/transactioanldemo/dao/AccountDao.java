package com.transactioanldemo.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void debit(int accNum, double amount) {
        String sql = "update account set balance = balance - ? where acc_no = ?";
        jdbcTemplate.update(sql, amount, accNum);
    }

    public void credit(int accNum, double amount) {
        String sql = "update account set balance = balance + ? where acc_no = ?";
        jdbcTemplate.update(sql, amount, accNum);
    }
}
