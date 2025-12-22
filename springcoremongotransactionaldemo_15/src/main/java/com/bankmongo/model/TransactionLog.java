package com.bankmongo.model;



import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class TransactionLog {

    private String txId;
    private String fromAcc;
    private String toAcc;
    private double amount;
    private String type;   // DEPOSIT / TRANSFER
    private String status; // SUCCESS / FAILED
    private String reason;
    private LocalDateTime time;

    // getters & setters
}
