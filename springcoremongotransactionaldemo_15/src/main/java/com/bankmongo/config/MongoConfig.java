package com.bankmongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public final class MongoConfig {

    private static final MongoDatabase DATABASE;

    static {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        DATABASE = client.getDatabase("bankdb");
        System.out.println("✅ Connected to MongoDB: " + DATABASE.getName());
    }

    private MongoConfig() {}

    public static MongoDatabase getDatabase() {
        return DATABASE;
    }
}
