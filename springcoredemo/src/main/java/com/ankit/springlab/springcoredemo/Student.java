package com.ankit.springlab.springcoredemo;

public class Student {

    public Student() {
        System.out.println("Student object created by Spring Container");
    }

    @Override
    public String toString() {
        return "This is Student Bean";
    }
}
