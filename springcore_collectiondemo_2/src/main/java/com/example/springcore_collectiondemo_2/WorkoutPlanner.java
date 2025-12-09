package com.example.springcore_collectiondemo_2;

import java.util.List;

public class WorkoutPlanner {

    private List<String> exercises;

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }

    public void displayWorkoutPlan() {
        System.out.println("Workout Plan:");
        for (String exercise : exercises) {
            System.out.println("- " + exercise);
        }

        if (exercises.size() > 5) {
            System.out.println("Intense Workout Plan");
        } else {
            System.out.println("Regular Workout Plan");
        }
    }
}
