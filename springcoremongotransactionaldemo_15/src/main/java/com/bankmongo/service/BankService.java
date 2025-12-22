package com.bankmongo.service;

import com.bankmongo.util.BankUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;

import static com.mongodb.client.model.Filters.eq;

public class BankService {

    private final MongoCollection<Document> userCol;
    private final MongoCollection<Document> logCol;

    public BankService(MongoDatabase db) {
        this.userCol = db.getCollection("users");
        this.logCol = db.getCollection("transaction_logs");
    }

    // ✅ Deposit
    public void deposit(String accNo, double amount) {

        Document user = userCol.find(eq("accountNo", accNo)).first();

        if (user == null) {
            System.out.println("❌ Account not found");
            return;
        }

        double oldBalance = user.getDouble("balance");

        try {
            user.put("balance", oldBalance + amount);
            userCol.replaceOne(eq("accountNo", accNo), user);

            log("NA", accNo, amount, "DEPOSIT", "SUCCESS", "Deposited");
            System.out.println("✅ Amount Deposited");

        } catch (Exception e) {

            // Manual rollback
            user.put("balance", oldBalance);
            userCol.replaceOne(eq("accountNo", accNo), user);

            log("NA", accNo, amount, "DEPOSIT", "FAILED", e.getMessage());
            System.out.println("❌ Deposit Failed");
        }
    }

    // 🔁 Transfer with Manual Rollback
    public void transfer(String fromAcc, String toAcc, double amount) {

        Document from = userCol.find(eq("accountNo", fromAcc)).first();
        Document to = userCol.find(eq("accountNo", toAcc)).first();

        if (from == null || to == null) {
            System.out.println("❌ Invalid account number");
            return;
        }

        double fromOld = from.getDouble("balance");
        double toOld = to.getDouble("balance");

        try {
            if (amount > 100000)
                throw new RuntimeException("Transfer limit exceeded");

            if (fromOld < amount)
                throw new RuntimeException("Insufficient balance");

            // debit
            from.put("balance", fromOld - amount);
            userCol.replaceOne(eq("accountNo", fromAcc), from);

            // simulate failure
            if (amount > 50000)
                throw new RuntimeException("System failure during transfer");

            // credit
            to.put("balance", toOld + amount);
            userCol.replaceOne(eq("accountNo", toAcc), to);

            log(fromAcc, toAcc, amount, "TRANSFER", "SUCCESS", "Completed");
            System.out.println("✅ Transfer Successful");

        } catch (Exception e) {

            // 🔥 MANUAL ROLLBACK
            from.put("balance", fromOld);
            to.put("balance", toOld);

            userCol.replaceOne(eq("accountNo", fromAcc), from);
            userCol.replaceOne(eq("accountNo", toAcc), to);

            log(fromAcc, toAcc, amount, "TRANSFER", "FAILED", e.getMessage());
            System.out.println("❌ Transfer Failed → Rollback Done");
        }
    }

    // ✅ Check Balance
    public void checkBalance(String accNo) {

        Document user = userCol.find(eq("accountNo", accNo)).first();

        if (user == null) {
            System.out.println("❌ Account not found");
            return;
        }

        System.out.println("💰 Balance: " + user.getDouble("balance"));
    }

    // ✅ Transaction Log
    private void log(String from, String to, double amt,
                     String type, String status, String reason) {

        Document log = new Document()
                .append("txId", BankUtil.generateTxId())
                .append("fromAcc", from)
                .append("toAcc", to)
                .append("amount", amt)
                .append("type", type)
                .append("status", status)
                .append("reason", reason)
                .append("time", LocalDateTime.now().toString());

        logCol.insertOne(log);
    }
}
