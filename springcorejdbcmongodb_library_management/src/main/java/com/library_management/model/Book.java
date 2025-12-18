package com.library_management.model;

public record Book(
        int bookId,
        String title,
        String author,
        String category,
        boolean availableStatus
) {}
