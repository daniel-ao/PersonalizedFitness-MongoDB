package com.mongodb.quickstart.PersonalizedFitness;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class ReadPersonalizedFitness {
    private final MongoCollection<Document> users;
    private final MongoCollection<Document> trainingPlans;

    // Constructor that accepts the MongoDB URI
    public ReadPersonalizedFitness(String uri) {
        if (uri == null || uri.isEmpty()) {
            throw new IllegalArgumentException("MongoDB URI cannot be null or empty.");
        }

        // Connect to MongoDB using the provided URI
        MongoClient mongoClient = MongoClients.create(uri);

        // Access the database
        MongoDatabase db = mongoClient.getDatabase("PersonalizedFitness");

        // Access the collections
        this.users = db.getCollection("Users");
        this.trainingPlans = db.getCollection("TrainingPlans");

        // Debugging: Verify collections
        System.out.println("Connected to database: " + db.getName());
        System.out.println("Users Collection: " + users.getNamespace());
        System.out.println("TrainingPlans Collection: " + trainingPlans.getNamespace());
    }

    // Method to read a user by UserID
    public void readUserById(int userId) {
        Document user = users.find(eq("UserID", userId)).first(); // Find the user by UserID
        if (user != null) {
            System.out.println("User Found: " + user.toJson()); // Print the user if found
        } else {
            System.out.println("User with UserID " + userId + " not found."); // Print not found message
        }
    }

    // Method to read a training plan by PlanID
    public void readTrainingPlanById(int planId) {
        Document trainingPlan = trainingPlans.find(eq("PlanID", planId)).first(); // Find the training plan by PlanID
        if (trainingPlan != null) {
            System.out.println("Training Plan Found: " + trainingPlan.toJson()); // Print the training plan if found
        } else {
            System.out.println("Training Plan with PlanID " + planId + " not found."); // Print not found message
        }
    }

    // Method to read all users
    public void readAllUsers() {
        System.out.println("All Users:");
        for (Document user : users.find()) { // Iterate through all users in the collection
            System.out.println(user.toJson());
        }
    }

    // Method to read all training plans
    public void readAllTrainingPlans() {
        System.out.println("All Training Plans:");
        for (Document plan : trainingPlans.find()) { // Iterate through all training plans in the collection
            System.out.println(plan.toJson());
        }
    }


// Public main for standalone testing and configuration
public static void main(String[] args) {
    // MongoDB connection URI
    String uri = System.getProperty("mongodb.uri");
    if (uri == null || uri.isEmpty()) {
        System.out.println("Error: MongoDB URI not provided.");
        return;
    }

    // Instantiate ReadPersonalizedFitness
    ReadPersonalizedFitness reader = new ReadPersonalizedFitness(uri);

    // Test reading a specific user by ID
    System.out.println("Testing readUserById with UserID 101:");
    reader.readUserById(101); // Replace 101 with a valid UserID to test

    System.out.println("\nTesting readUserById with a non-existent UserID 999:");
    reader.readUserById(999); // Test for non-existent UserID

    // Test reading a specific training plan by ID
    System.out.println("\nTesting readTrainingPlanById with PlanID 1:");
    reader.readTrainingPlanById(1); // Replace 1 with a valid PlanID to test

    System.out.println("\nTesting readTrainingPlanById with a non-existent PlanID 999:");
    reader.readTrainingPlanById(999); // Test for non-existent PlanID

    // Test reading all users
    System.out.println("\nTesting readAllUsers:");
    reader.readAllUsers(); // Retrieve and display all users

    // Test reading all training plans
    System.out.println("\nTesting readAllTrainingPlans:");
    reader.readAllTrainingPlans(); // Retrieve and display all training plans
}

}
