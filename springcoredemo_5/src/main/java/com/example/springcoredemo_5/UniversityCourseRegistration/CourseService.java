package com.example.springcoredemo_5.UniversityCourseRegistration;

import java.util.List;

public class CourseService {
    private Student student;
    private List<String> courseList;
    
     public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourses(List<String> courses) {
        this.courseList = courses;
    }

    public void displayStudentCourses() {
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Student Name: " + student.getStudentName());
        System.out.println("Registered Courses: ");
        for (String course : courseList) {
            System.out.println("- " + course);
        }
    }
    
}
