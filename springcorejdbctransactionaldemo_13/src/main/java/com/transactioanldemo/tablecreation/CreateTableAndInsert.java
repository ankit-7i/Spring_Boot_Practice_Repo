package com.transactioanldemo.tablecreation;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTableAndInsert {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/springcoredemo";
        String username = "root";
        String password = "Ankit07%";

        try {
    
            Class.forName("com.mysql.cj.jdbc.Driver");

        
            Connection con = DriverManager.getConnection(url, username, password);

            Statement stmt = con.createStatement();

        
            String createTable =
                    "CREATE TABLE IF NOT EXISTS account (" +
                    "acc_no INT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "balance DOUBLE)";

            stmt.executeUpdate(createTable);

    
            String insert1 =
                    "INSERT INTO account VALUES (101, 'Ankit', 100000)";
            String insert2 =
                    "INSERT INTO account VALUES (102, 'Rahul', 50000)";

            stmt.executeUpdate(insert1);
            stmt.executeUpdate(insert2);

            System.out.println("Table created and data inserted successfully");

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

