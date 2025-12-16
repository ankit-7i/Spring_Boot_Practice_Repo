package com.example.springcoredemo_5.UniversityCourseRegistration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        CourseService cs = (CourseService) context.getBean("courseService");

        cs.displayStudentCourses();
    }

}
