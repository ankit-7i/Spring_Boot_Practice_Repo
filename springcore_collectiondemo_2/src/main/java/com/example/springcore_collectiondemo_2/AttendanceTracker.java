package com.example.springcore_collectiondemo_2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AttendanceTracker {

    private final Map<String, Integer> attendanceMap;

    public AttendanceTracker(Map<String, Integer> attendanceMap) {
        this.attendanceMap = attendanceMap;
    }

    public List<String> getDefaulters() {
        return attendanceMap.entrySet()
                .stream()
                .filter(e -> e.getValue() < 75)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}