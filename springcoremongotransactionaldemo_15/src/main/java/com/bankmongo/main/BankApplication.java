package com.bankmongo.main;

import java.util.Scanner;
import com.bankmongo.config.MongoConfig;
import com.bankmongo.model.BankUser;
import com.bankmongo.service.BankService;
import com.bankmongo.util.BankUtil;
import com.mongodb.client.MongoCollection;

public class BankApplication {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        var db = MongoConfig.getDatabase();
        BankService service = new BankService(db);
        MongoCollection<BankUser> users = db.getCollection("users", BankUser.class);

        while (true) {
            System.out.println("""
                \n--- BANK MENU ---
                1. Create Account
                2. Deposit
                3. Transfer
                4. Check Balance
                5. Exit
                """);

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    BankUser u = new BankUser();
                    System.out.print("First Name: "); u.setFirstName(sc.next());
                    System.out.print("Last Name: "); u.setLastName(sc.next());
                    System.out.print("DOB(YYYY-MM-DD): "); u.setDob(sc.next());
                    System.out.print("Mobile: "); u.setMobile(sc.next());
                    System.out.print("Aadhar: "); u.setAadhar(sc.next());
                    System.out.print("Address: "); u.setAddress(sc.next());
                    System.out.print("Zip: "); u.setZip(sc.next());

                    u.setAccountNo(BankUtil.generateAccountNo());
                    u.setUsername(BankUtil.generateUsername(u.getFirstName(), u.getDob()));
                    u.setPassword(BankUtil.generatePassword());
                    u.setBalance(0);

                    users.insertOne(u);

                    System.out.println("✅ Account Created");
                    System.out.println("Account No: " + u.getAccountNo());
                    System.out.println("Username: " + u.getUsername());
                    System.out.println("Password: " + u.getPassword());
                }

                case 2 -> {
                    System.out.print("Account No: ");
                    service.deposit(sc.next(), sc.nextDouble());
                }

                case 3 -> {
                    System.out.print("From Acc: "); String f = sc.next();
                    System.out.print("To Acc: "); String t = sc.next();
                    System.out.print("Amount: "); double a = sc.nextDouble();
                    service.transfer(f, t, a);
                }

                case 4 -> {
                    System.out.print("Account No: ");
                    service.checkBalance(sc.next());
                }

                case 5 -> {
                    System.out.println("🙏 Thank You");
                    System.exit(0);
                }
            }
        }
    }
}
