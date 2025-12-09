package com.example.springcore_collectiondemo_2.TestingFolder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springcore_collectiondemo_2.WorkoutPlanner;

public class WorkoutPlanTest {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");

        WorkoutPlanner planner = (WorkoutPlanner) context.getBean("workoutPlanner");
        planner.displayWorkoutPlan();
    }
}
