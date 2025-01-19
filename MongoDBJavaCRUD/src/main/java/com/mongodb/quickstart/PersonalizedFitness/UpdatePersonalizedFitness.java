package com.mongodb.quickstart.PersonalizedFitness;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class UpdatePersonalizedFitness {

    protected final MongoCollection<Document> users;
    protected final MongoCollection<Document> trainingPlans;

    // Constructor that accepts the MongoDB URI
    public UpdatePersonalizedFitness(String uri) {
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

    // Method to update a user's name by UserID
    public void updateUserName(int userId, String newName) {
        UpdateResult result = users.updateOne(eq("UserID", userId), set("Name", newName));
        if (result.getMatchedCount() > 0) {
            System.out.println("Updated UserID " + userId + " with new Name: " + newName);
        } else {
            System.out.println("User with UserID " + userId + " not found. No update performed.");
        }
    }

    // Method to update a training plan's goal by PlanID
    public void updateTrainingPlanGoal(int planId, String newGoal) {
        UpdateResult result = trainingPlans.updateOne(eq("PlanID", planId), set("Goal", newGoal));
        if (result.getMatchedCount() > 0) {
            System.out.println("Updated Training PlanID " + planId + " with new Goal: " + newGoal);
        } else {
            System.out.println("Training Plan with PlanID " + planId + " not found. No update performed.");
        }
    }

    // Method to update a user's training plan
    public void updateUserTrainingPlan(int userId, int newTrainingPlanId) {
        UpdateResult result = users.updateOne(eq("UserID", userId), set("TrainingPlanID", newTrainingPlanId));
        if (result.getMatchedCount() > 0) {
            System.out.println("Updated UserID " + userId + " with new TrainingPlanID: " + newTrainingPlanId);
        } else {
            System.out.println("User with UserID " + userId + " not found. No update performed.");
        }
    }

    // Public main for debugging and testing purposes
    public static void main(String[] args) {
        // MongoDB connection URI
        String uri = System.getProperty("mongodb.uri");
        if (uri == null || uri.isEmpty()) {
            System.out.println("Error: MongoDB URI not provided.");
            return;
        }

        // Instantiate UpdatePersonalizedFitness
        UpdatePersonalizedFitness updater = new UpdatePersonalizedFitness(uri);

        // Test updating a user's name
        System.out.println("Testing updateUserName with UserID 101:");
        updater.updateUserName(101, "UpdatedName101");

        System.out.println("\nTesting updateUserName with a non-existent UserID 999:");
        updater.updateUserName(999, "NonExistentName");

        // Test updating a training plan's goal
        System.out.println("\nTesting updateTrainingPlanGoal with PlanID 1:");
        updater.updateTrainingPlanGoal(1, "Updated Goal 1");

        System.out.println("\nTesting updateTrainingPlanGoal with a non-existent PlanID 999:");
        updater.updateTrainingPlanGoal(999, "NonExistentGoal");

        // Test updating a user's training plan
        System.out.println("\nTesting updateUserTrainingPlan with UserID 101:");
        updater.updateUserTrainingPlan(101, 2);

        System.out.println("\nTesting updateUserTrainingPlan with a non-existent UserID 999:");
        updater.updateUserTrainingPlan(999, 2);
    }
}
