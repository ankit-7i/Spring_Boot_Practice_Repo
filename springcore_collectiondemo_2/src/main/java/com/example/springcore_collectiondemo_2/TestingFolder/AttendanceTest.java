package com.example.springcore_collectiondemo_2.TestingFolder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcore_collectiondemo_2.AttendanceTracker;

public class AttendanceTest {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");

        AttendanceTracker tracker =
                (AttendanceTracker) context.getBean("attendanceTracker");

        System.out.println("Defaulters (attendance < 75%):");
        tracker.getDefaulters().forEach(System.out::println);
    }

    
}
