package com.mongodb.quickstart.PersonalizedFitness;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DeleteEntireDatabase {

    private final MongoDatabase database;

    // Constructor to initialize the database connection
    public DeleteEntireDatabase(String uri) {
        if (uri == null || uri.isEmpty()) {
            throw new IllegalArgumentException("MongoDB URI cannot be null or empty.");
        }

        // Connect to MongoDB using the provided URI
        MongoClient mongoClient = MongoClients.create(uri);

        // Access the database
        this.database = mongoClient.getDatabase("PersonalizedFitness");

        // Debugging: Verify connection
        System.out.println("Connected to database: " + database.getName());
    }

    // Method to delete the entire database with internal password protection
    public void deleteDatabase() {
        System.out.println("WARNING: This action will delete the entire database.");
        System.out.print("Enter the password to proceed: ");

        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            String providedPassword = scanner.nextLine(); // Read password input
            // Private password for deletion
            String requiredPassword = "Dano2003";
            if (!requiredPassword.equals(providedPassword)) {
                System.out.println("Error: Incorrect password. Access denied.");
                return;
            }


            // Confirm deletion
            System.out.print("Type 'CONFIRM' to delete the database: ");
            String confirmation = scanner.nextLine();
            if ("CONFIRM".equalsIgnoreCase(confirmation)) {
                database.drop(); // Drop the entire database
                System.out.println("The database '" + database.getName() + "' has been successfully deleted.");
            } else {
                System.out.println("Action cancelled. The database was not deleted.");
            }
        }
    }

    // Public main for standalone testing (skips password prompt for safety)
    public static void main(String[] args) {
        // MongoDB connection URI
        String uri = System.getProperty("mongodb.uri");
        if (uri == null || uri.isEmpty()) {
            System.out.println("Error: MongoDB URI not provided.");
            return;
        }

        /*
        // Testing without password protection
        DeleteEntireDatabase deleter = new DeleteEntireDatabase(uri);
        System.out.println("Testing database deletion (no password protection).");
        deleter.database.drop();*/
        System.out.println("Database deleted for testing purposes.");
    }
}
