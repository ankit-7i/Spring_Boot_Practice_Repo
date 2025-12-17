package com.example.springcorejdbcmysqldemo_7;



import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class CreateTable {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext con =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        JdbcTemplate jdbcTemplate = con.getBean(JdbcTemplate.class);

        String sql =
                "CREATE TABLE IF NOT EXISTS student (" +
                "sid INT PRIMARY KEY, " +
                "sname VARCHAR(50), " +
                "saddress VARCHAR(100), " +
                "marks DOUBLE" +
                ")";

        jdbcTemplate.execute(sql);

        System.out.println("✅ Student table created successfully");

        con.close();
    }
}
