package com.bankmongo.model;


import org.bson.types.ObjectId;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BankUser {

    private ObjectId id;
    private String accountNo;
    private String firstName;
    private String lastName;
    private String dob;
    private String mobile;
    private String aadhar;
    private String address;
    private String zip;
    private String username;
    private String password;
    private double balance;

    // getters & setters
}
