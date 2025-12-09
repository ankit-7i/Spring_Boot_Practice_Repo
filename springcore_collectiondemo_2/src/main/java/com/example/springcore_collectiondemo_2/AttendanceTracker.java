package com.example.springcore_collectiondemo_2;

import java.util.Map;

public class AttendanceTracker {
    private Map<String, Integer> attendanceMap;

    public AttendanceTracker(Map<String, Integer> attendanceMap) {
        this.attendanceMap = attendanceMap;
    }
    
}