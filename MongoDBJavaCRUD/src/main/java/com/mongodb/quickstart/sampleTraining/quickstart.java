package com.mongodb.quickstart.sampleTraining;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class quickstart {
    public static void main(String[] args) {
        // Connection string for MongoDB (default localhost:27017)
        String connectionString = "mongodb://localhost:27017";

        // Connect to MongoDB
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Access a database
            MongoDatabase database = mongoClient.getDatabase("test_db");
            System.out.println("Connected to database: " + database.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
