package com.mongodb.quickstart.PersonalizedFitness;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;

public class DeletePersonalizedFitness extends UpdatePersonalizedFitness {

    // Constructor that passes the MongoDB URI to the parent class
    public DeletePersonalizedFitness(String uri) {
        super(uri); // Call the parent constructor to initialize collections
    }

    // Method to delete a user by UserID
    public void deleteUserById(int userId) {
        DeleteResult result = users.deleteOne(eq("UserID", userId));
        if (result.getDeletedCount() > 0) {
            System.out.println("User with UserID " + userId + " has been deleted.");
        } else {
            System.out.println("User with UserID " + userId + " not found. No deletion performed.");
        }
    }

    // Method to delete a training plan by PlanID and reassign users
    public void deleteTrainingPlanById(int planId) {
        // Check if the training plan exists
        Document trainingPlan = trainingPlans.find(eq("PlanID", planId)).first();
        if (trainingPlan == null) {
            System.out.println("Training Plan with PlanID " + planId + " not found. No deletion performed.");
            return;
        }

        // Get all remaining training plans
        List<Integer> remainingPlanIds = new ArrayList<>();
        for (Document plan : trainingPlans.find()) {
            int currentPlanId = plan.getInteger("PlanID");
            if (currentPlanId != planId) { // Exclude the plan being deleted
                remainingPlanIds.add(currentPlanId);
            }
        }

        if (remainingPlanIds.isEmpty()) {
            System.out.println("Cannot delete Training Plan " + planId + " as no other plans are available to reassign.");
            return;
        }

        // Reassign users with the deleted training plan
        Random random = new Random();
        for (Document user : users.find(eq("TrainingPlanID", planId))) {
            int newPlanId = remainingPlanIds.get(random.nextInt(remainingPlanIds.size())); // Randomly assign a new plan
            updateUserTrainingPlan(user.getInteger("UserID"), newPlanId); // Use inherited method
        }

        // Delete the training plan
        DeleteResult result = trainingPlans.deleteOne(eq("PlanID", planId));
        if (result.getDeletedCount() > 0) {
            System.out.println("Training Plan with PlanID " + planId + " has been deleted.");
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

        // Instantiate DeletePersonalizedFitness
        DeletePersonalizedFitness deleter = new DeletePersonalizedFitness(uri);

        // Test deleting a user
        System.out.println("Testing deleteUserById with UserID 101:");
        deleter.deleteUserById(101);

        System.out.println("\nTesting deleteUserById with a non-existent UserID 999:");
        deleter.deleteUserById(999);

        // Test deleting a training plan
        System.out.println("\nTesting deleteTrainingPlanById with PlanID 1:");
        deleter.deleteTrainingPlanById(1);

        System.out.println("\nTesting deleteTrainingPlanById with a non-existent PlanID 999:");
        deleter.deleteTrainingPlanById(999);
    }
}
