package com.bankmongo.main;

import com.bankmongo.config.MongoConfig;
import com.bankmongo.service.BankService;
import com.bankmongo.util.BankUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class BankApplication {

    public static void main(String[] args) {

        MongoDatabase db = MongoConfig.getDatabase();
        BankService service = new BankService(db);

        MongoCollection<Document> users = db.getCollection("users");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
                    --- BANK MENU ---
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
                    System.out.print("First Name: ");
                    String firstName = sc.next();

                    System.out.print("Last Name: ");
                    String lastName = sc.next();

                    System.out.print("DOB (YYYY-MM-DD): ");
                    String dob = sc.next();

                    System.out.print("Mobile: ");
                    String mobile = sc.next();

                    System.out.print("Aadhar: ");
                    String aadhar = sc.next();

                    System.out.print("Address: ");
                    String address = sc.next();

                    System.out.print("Zip: ");
                    String zip = sc.next();

                    String accountNo = BankUtil.generateAccountNo();
                    String username = BankUtil.generateUsername(firstName, dob);
                    String password = BankUtil.generatePassword();

                    Document userDoc = new Document()
                            .append("accountNo", accountNo)
                            .append("firstName", firstName)
                            .append("lastName", lastName)
                            .append("dob", dob)
                            .append("mobile", mobile)
                            .append("aadhar", aadhar)
                            .append("address", address)
                            .append("zip", zip)
                            .append("username", username)
                            .append("password", password)
                            .append("balance", 0.0);

                    users.insertOne(userDoc);

                    System.out.println("✅ Account Created Successfully");
                    System.out.println("Account No : " + accountNo);
                    System.out.println("Username   : " + username);
                    System.out.println("Password   : " + password);
                }

                case 2 -> {
                    System.out.print("Account No: ");
                    String accNo = sc.next();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    service.deposit(accNo, amt);
                }

                case 3 -> {
                    System.out.print("From Account: ");
                    String from = sc.next();
                    System.out.print("To Account: ");
                    String to = sc.next();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    service.transfer(from, to, amt);
                }

                case 4 -> {
                    System.out.print("Account No: ");
                    service.checkBalance(sc.next());
                }

                case 5 -> {
                    System.out.println("🙏 Thank you for using Bank App");
                    System.exit(0);
                }
            }
        }
    }
}
