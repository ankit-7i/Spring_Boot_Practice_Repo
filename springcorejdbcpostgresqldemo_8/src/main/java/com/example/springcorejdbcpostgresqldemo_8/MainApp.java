package com.example.springcorejdbcpostgresqldemo_8;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.springcorejdbcpostgresqldemo_8.config.JdbcConfig;
import com.example.springcorejdbcpostgresqldemo_8.dao.StudentOperations;
import com.example.springcorejdbcpostgresqldemo_8.model.Student;  

public class MainApp {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(JdbcConfig.class);

        StudentOperations ops = ctx.getBean(StudentOperations.class);

        ops.createTable();

        ops.addStudent(new Student(1, "Rahman", 85));
        System.out.println(ops.getStudent(1));

        ops.updateStudent(new Student(1, "Rahman Khan", 92));

        ops.getAllStudents().forEach(System.out::println);

       // ops.deleteStudent(1);
    }
}

