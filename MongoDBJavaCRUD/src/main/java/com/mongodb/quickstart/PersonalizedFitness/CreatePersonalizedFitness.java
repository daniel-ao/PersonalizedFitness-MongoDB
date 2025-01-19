package com.mongodb.quickstart.PersonalizedFitness;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;

public class CreatePersonalizedFitness {

    private final MongoCollection<Document> users;
    private final MongoCollection<Document> trainingPlans;

    // Constructor for dynamic usage
    public CreatePersonalizedFitness(String uri) {
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase db = mongoClient.getDatabase("PersonalizedFitness");
        this.users = db.getCollection("Users");
        this.trainingPlans = db.getCollection("TrainingPlans");

        System.out.println("Connected to PersonalizedFitness database.");
    }

    // Method to add a single user dynamically
    public void addUser(int userId, String name, int age, int trainingPlanId) {
        if (users.find(eq("UserID", userId)).first() == null) {
            Document user = new Document("UserID", userId)
                    .append("Name", name)
                    .append("Age", age)
                    .append("TrainingPlanID", trainingPlanId);
            users.insertOne(user);
            System.out.println("Added User: " + user.toJson());
        } else {
            System.out.println("User with UserID " + userId + " already exists. Skipping...");
        }
    }

    // Static method to run default data creation
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase db = mongoClient.getDatabase("PersonalizedFitness");

            // Collections
            MongoCollection<Document> users = db.getCollection("Users");
            MongoCollection<Document> trainingPlans = db.getCollection("TrainingPlans");

            // Define 5 unique training plans
            List<Document> predefinedTrainingPlans = List.of(
                    new Document("PlanID", 1)
                            .append("Goal", "Weight Loss")
                            .append("Exercises", List.of(
                                    new Document("Name", "Cardio Routine").append("Duration", 45).append("CaloriesBurned", 300),
                                    new Document("Name", "Jumping Jacks").append("Duration", 15).append("CaloriesBurned", 150))),
                    new Document("PlanID", 2)
                            .append("Goal", "Muscle Gain")
                            .append("Exercises", List.of(
                                    new Document("Name", "Weight Lifting").append("Duration", 60).append("CaloriesBurned", 250),
                                    new Document("Name", "Push-Ups").append("Duration", 20).append("CaloriesBurned", 100))),
                    new Document("PlanID", 3)
                            .append("Goal", "Endurance")
                            .append("Exercises", List.of(
                                    new Document("Name", "Long-Distance Running").append("Duration", 90).append("CaloriesBurned", 500),
                                    new Document("Name", "Cycling").append("Duration", 60).append("CaloriesBurned", 400))),
                    new Document("PlanID", 4)
                            .append("Goal", "Flexibility")
                            .append("Exercises", List.of(
                                    new Document("Name", "Yoga").append("Duration", 60).append("CaloriesBurned", 200),
                                    new Document("Name", "Stretching").append("Duration", 30).append("CaloriesBurned", 100))),
                    new Document("PlanID", 5)
                            .append("Goal", "General Fitness")
                            .append("Exercises", List.of(
                                    new Document("Name", "Circuit Training").append("Duration", 45).append("CaloriesBurned", 350),
                                    new Document("Name", "Burpees").append("Duration", 15).append("CaloriesBurned", 150)))
            );

            // Insert the training plans into the database
            System.out.println("Ensuring TrainingPlans collection has 5 unique entries...");
            for (Document trainingPlan : predefinedTrainingPlans) {
                if (trainingPlans.find(eq("PlanID", trainingPlan.getInteger("PlanID"))).first() == null) {
                    trainingPlans.insertOne(trainingPlan);
                    System.out.println("Inserted TrainingPlan with PlanID: " + trainingPlan.getInteger("PlanID"));
                } else {
                    System.out.println("TrainingPlan with PlanID " + trainingPlan.getInteger("PlanID") + " already exists. Skipping...");
                }
            }

            // Name list for Users
            List<String> names = List.of("Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hank", "Ivy", "Jack",
                    "Kate", "Leo", "Mona", "Nick", "Olivia");
            Random random = new Random();

            // Insert 15 unique users
            System.out.println("\nEnsuring Users collection has 15 unique entries...");
            for (int i = 1; i <= 15; i++) {
                if (users.find(eq("UserID", 100 + i)).first() == null) {
                    String name = names.get((i - 1) % names.size()); // Cycle through the name list
                    int age = 18 + random.nextInt(33); // Random age between 18 and 50
                    int trainingPlanId = (i % 5) + 1; // Assign a valid TrainingPlanID (1 to 5)
                    Document user = new Document("UserID", 100 + i)
                            .append("Name", name)
                            .append("Age", age)
                            .append("TrainingPlanID", trainingPlanId);
                    users.insertOne(user);
                    System.out.println("Inserted User with UserID: " + (100 + i) + ", Name: " + name + ", Age: " + age + ", TrainingPlanID: " + trainingPlanId);
                } else {
                    System.out.println("User with UserID " + (100 + i) + " already exists. Skipping...");
                }
            }

            System.out.println("\nData creation complete.");
        }
    }
}
