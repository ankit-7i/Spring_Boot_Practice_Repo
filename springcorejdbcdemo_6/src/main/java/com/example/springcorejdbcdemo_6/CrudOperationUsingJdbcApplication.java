package com.example.springcorejdbcdemo_6;



import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.springcorejdbc.config.AppConfig;
import com.example.springcorejdbc.service.EmployeeService;

public class CrudOperationUsingJdbcApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        EmployeeService service = ctx.getBean(EmployeeService.class);

        // service.createTable();
        service.insertData();

        ctx.close();
    }
}

