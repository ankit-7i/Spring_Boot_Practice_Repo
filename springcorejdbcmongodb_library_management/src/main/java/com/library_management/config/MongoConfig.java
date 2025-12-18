package com.library_management.config;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public final class MongoConfig {

    private static final MongoDatabase DATABASE;

    static {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        DATABASE = client.getDatabase("librarydb");
        System.out.println("Connected to MongoDB database: " + DATABASE.getName());
    }

    private MongoConfig() {}

    public static MongoDatabase getDatabase() {
        return DATABASE;
    }
}