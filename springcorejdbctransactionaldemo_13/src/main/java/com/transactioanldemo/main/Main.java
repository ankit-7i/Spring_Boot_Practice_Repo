package com.transactioanldemo.main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.transactioanldemo.config.SpringConfigfile;
import com.transactioanldemo.service.BankService;

public class Main {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        AnnotationConfigApplicationContext con =
                new AnnotationConfigApplicationContext(SpringConfigfile.class);

        BankService bankService = con.getBean(BankService.class);
        bankService.tranferMoney(101, 102, 50000);

        System.out.println("Transaction successfully completed");
    }
}
