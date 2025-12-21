package com.transactioanldemo.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.transactioanldemo")
@EnableTransactionManagement
public class SpringConfigfile {

    @Bean
    public DriverManagerDataSource addDMD() {
        DriverManagerDataSource add = new DriverManagerDataSource();
        add.setDriverClassName("com.mysql.cj.jdbc.Driver");
        add.setUrl("jdbc:mysql://localhost:3306/springcoredemo");
        add.setUsername("root");
        add.setPassword("Ankit07%");
        return add;
    }

    @Bean
    public JdbcTemplate jdbcTemp() {
        return new JdbcTemplate(addDMD());
    }

    @Bean
    public PlatformTransactionManager ptManag() {
        return new DataSourceTransactionManager(addDMD());
    }
}
