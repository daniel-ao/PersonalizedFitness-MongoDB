package com.mongodb.quickstart.PersonalizedFitness;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

public static void main(String[] args) {

        // MongoDB connection URI
        String uri = System.getProperty("mongodb.uri"); // Retrieves the MongoDB URI from system properties
        if (uri == null || uri.isEmpty()) { // Checks if the URI is provided
            System.out.println("Error: MongoDB URI not provided."); // Displays an error if URI is missing
            return; // Exits the program
        }

        // Step 1: Create new users using CreatePersonalizedFitness
        // Instantiate CreatePersonalizedFitness class for adding users and plans
        CreatePersonalizedFitness creator = new CreatePersonalizedFitness(uri);

        // Instantiate ReadPersonalizedFitness class for reading data
        ReadPersonalizedFitness reader = new ReadPersonalizedFitness(uri);

        // Instantiate UpdatePersonalizedFitness
        UpdatePersonalizedFitness updater = new UpdatePersonalizedFitness(uri);

        // Instantiate DeletePersonalizedFitness
        DeletePersonalizedFitness deleter = new DeletePersonalizedFitness(uri);

        // Instantiate DeleteEntireDatabase
        DeleteEntireDatabase dbDeleter = new DeleteEntireDatabase(uri);


        // Dynamically add a new user
        // Example: Adding a user with UserID 117, Name "Daniel", Age 21, and TrainingPlanID 5
        creator.addUser(117, "Daniel", 21, 5);

        // Step 2: Read data using ReadPersonalizedFitness


        // Read a specific user by UserID
        System.out.println("\nReading User with UserID 117:");
        reader.readUserById(117);

        // Read a specific training plan by PlanID
        System.out.println("\nReading Training Plan with PlanID 5:");
        reader.readTrainingPlanById(5);

        // Read all users in the database
        System.out.println("\nReading all Users:");
        reader.readAllUsers();

        // Read all training plans in the database
        System.out.println("\nReading all Training Plans:");
        reader.readAllTrainingPlans();


        // Example: Update a user's name
        updater.updateUserName(101, "Alice");

        // Example: Update a training plan's goal
        updater.updateTrainingPlanGoal(3, "Updated Endurance Goal");

        // Example: Update a user's training plan
        updater.updateUserTrainingPlan(104, 4);


        // Example: Delete a user
        deleter.deleteUserById(102);

        // Example: Delete a training plan
        deleter.deleteTrainingPlanById(3);




        /*############################################*/
        /*           DataBase Deletion                */
        // Attempt to delete the database (password and confirmation are handled inside the class)
        //dbDeleter.deleteDatabase();
    }
}
