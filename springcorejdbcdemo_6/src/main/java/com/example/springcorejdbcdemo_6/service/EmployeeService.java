package com.example.springcorejdbcdemo_6.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    private static Connection con;

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl",
                    "jdbcdb",
                    "ankit07"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "CREATE TABLE emp_data01 (" +
                    "eid NUMBER(5), " +
                    "ename VARCHAR2(20), " +
                    "address VARCHAR2(30), " +
                    "salary NUMBER(9,2))"
            );
            pstmt.executeUpdate();
            System.out.println("Table created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertData() {
        try {
            PreparedStatement pstmt =
                    con.prepareStatement("INSERT INTO emp_data01 VALUES (?,?,?,?)");

            pstmt.setInt(1, 101);
            pstmt.setString(2, "Rajat");
            pstmt.setString(3, "Hyderabad");
            pstmt.setDouble(4, 50000);

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
