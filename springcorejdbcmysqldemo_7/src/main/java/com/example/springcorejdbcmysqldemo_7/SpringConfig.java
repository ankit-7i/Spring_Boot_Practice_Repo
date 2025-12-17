package com.example.springcorejdbcmysqldemo_7;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfig {

    @Bean
    public DriverManagerDataSource addDMD() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/springcoredemo");
        ds.setUsername("root");
        ds.setPassword("@nkit07");
        return ds;
    }

    @Bean
    public JdbcTemplate createTemplate() {
        return new JdbcTemplate(addDMD());
    }
}