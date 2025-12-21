package com.transactioanldemo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transactioanldemo.dao.AccountDao;

@Service
public class BankService {

    @Autowired
    AccountDao accountDao;

    @Transactional(rollbackFor = Exception.class)
    public void tranferMoney(int fromAcc, int toAcc, double amount) {

        accountDao.debit(fromAcc, amount);
        System.out.println("amount debit from account: " + fromAcc);

        if (amount > 10000) {
            throw new RuntimeException("Amount too large your Transaction-Failed");
        }

        accountDao.credit(toAcc, amount);
        System.out.println("amount credit account: " + toAcc);
    }
}
