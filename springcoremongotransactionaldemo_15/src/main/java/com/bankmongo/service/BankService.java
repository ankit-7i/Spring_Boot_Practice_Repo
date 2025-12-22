package com.bankmongo.service;

import com.bankmongo.model.BankUser;
import com.bankmongo.model.TransactionLog;
import com.bankmongo.util.BankUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDateTime;

public class BankService {

    private final MongoCollection<BankUser> userCol;
    private final MongoCollection<TransactionLog> logCol;

    public BankService(MongoDatabase db) {
        userCol = db.getCollection("users", BankUser.class);
        logCol = db.getCollection("transaction_logs", TransactionLog.class);
    }

    // ✅ Deposit
    public void deposit(String accNo, double amount) {

        BankUser user = userCol.find(eq("accountNo", accNo)).first();
        double oldBalance = user.getBalance();

        try {
            user.setBalance(oldBalance + amount);
            userCol.replaceOne(eq("accountNo", accNo), user);

            log("NA", accNo, amount, "DEPOSIT", "SUCCESS", "Deposited");

            System.out.println("✅ Amount Deposited");

        } catch (Exception e) {
            user.setBalance(oldBalance);
            userCol.replaceOne(eq("accountNo", accNo), user);
            log("NA", accNo, amount, "DEPOSIT", "FAILED", e.getMessage());
        }
    }

    // 🔁 Transfer with Manual Rollback
    public void transfer(String fromAcc, String toAcc, double amount) {

        BankUser from = userCol.find(eq("accountNo", fromAcc)).first();
        BankUser to = userCol.find(eq("accountNo", toAcc)).first();

        double fromOld = from.getBalance();
        double toOld = to.getBalance();

        try {
            if (amount > 100000)
                throw new RuntimeException("Transfer limit exceeded");

            if (fromOld < amount)
                throw new RuntimeException("Insufficient balance");

            from.setBalance(fromOld - amount);
            userCol.replaceOne(eq("accountNo", fromAcc), from);

            if (amount > 50000)
                throw new RuntimeException("System failure");

            to.setBalance(toOld + amount);
            userCol.replaceOne(eq("accountNo", toAcc), to);

            log(fromAcc, toAcc, amount, "TRANSFER", "SUCCESS", "Completed");
            System.out.println("✅ Transfer Successful");

        } catch (Exception e) {

            // 🔥 MANUAL ROLLBACK
            from.setBalance(fromOld);
            to.setBalance(toOld);
            userCol.replaceOne(eq("accountNo", fromAcc), from);
            userCol.replaceOne(eq("accountNo", toAcc), to);

            log(fromAcc, toAcc, amount, "TRANSFER", "FAILED", e.getMessage());
            System.out.println("❌ Transfer Failed → Rollback Done");
        }
    }

    public void checkBalance(String accNo) {
        BankUser user = userCol.find(eq("accountNo", accNo)).first();
        System.out.println("💰 Balance: " + user.getBalance());
    }

    private void log(String from, String to, double amt,
                     String type, String status, String reason) {

        TransactionLog log = new TransactionLog();
        log.setTxId(BankUtil.generateTxId());
        log.setFromAcc(from);
        log.setToAcc(to);
        log.setAmount(amt);
        log.setType(type);
        log.setStatus(status);
        log.setReason(reason);
        log.setTime(LocalDateTime.now());

        logCol.insertOne(log);
    }
}
