# MongoDB-PersonalizedFitness

A Java-based project leveraging MongoDB for managing personalized fitness data. This project demonstrates CRUD operations (Create, Read, Update, Delete) on a MongoDB Atlas database, including dynamic user and training plan management, with a focus on scalability and practical implementation.

---

## Project Overview

The `PersonalizedFitness` project is the primary focus of this repository. It extends the concepts learned in an earlier `sampleTraining` phase and applies them to a real-world scenario. The aim of this project is to manage and analyze fitness-related data, showcasing advanced MongoDB operations combined with Java to create a functional application.

Key highlights include:
- Designing a database schema tailored for fitness data, such as user profiles, workout plans, and progress tracking.
- Implementing CRUD operations to handle fitness data efficiently.
- Utilizing advanced MongoDB features like aggregations and indexing for performance optimization.

---

## Features

1. **Environment Setup**:
   - Uses **IntelliJ IDEA** as the primary IDE.
   - Maven is used for dependency management, including the MongoDB Java Driver.
   - MongoDB Atlas hosts the database.

2. **Core Functionalities**:
   - **Create Operations**:
     - Predefined training plans with specific goals and exercises.
     - User profiles dynamically created with unique `UserID`, `Name`, `Age`, and assigned `TrainingPlanID`.
   - **Read Operations**:
     - Query individual or all users and training plans.
     - Display results in JSON format.
   - **Update Operations**:
     - Modify user names, training plan goals, or assigned plans.
   - **Delete Operations**:
     - Remove specific users or training plans.
     - Automatically reassign users affected by training plan deletions.
   - **Database Deletion**:
     - Securely delete the entire database with password protection and user confirmation.

3. **Integration of Classes**:
   - The `Main` class serves as the entry point for testing all features in a unified workflow.

---

## Project Structure

The repository is organized as follows:
```
src/
└── main/
    └── java/
        └── com/
            └── mongodb/
                └── quickstart/
                    └── PersonalizedFitness/
                        ├── CreatePersonalizedFitness.java
                        ├── ReadPersonalizedFitness.java
                        ├── UpdatePersonalizedFitness.java
                        ├── DeletePersonalizedFitness.java
                        ├── DeleteEntireDatabase.java
                        ├── Main.java
```

- **`CreatePersonalizedFitness`**: Manages database population with users and training plans.
- **`ReadPersonalizedFitness`**: Handles data retrieval for users and training plans.
- **`UpdatePersonalizedFitness`**: Allows updating specific fields in users or training plans.
- **`DeletePersonalizedFitness`**: Manages user and training plan deletion with reassignment logic.
- **`DeleteEntireDatabase`**: Implements secure deletion of the entire database.
- **`Main`**: Integrates all features for testing and demonstration.

---

## Usage Instructions

1. Clone this repository:
   ```bash
   git clone https://github.com/daniel-ao/MongoDB-PersonalizedFitness.git
   ```

2. Open the project in IntelliJ IDEA.

3. Ensure the MongoDB Atlas cluster connection URI is set as a system property:
   ```bash
   -Dmongodb.uri=mongodb+srv://<USR>:<PASS>@cluster0.rqswf.mongodb.net/PersonalizedFitness
   ```

4. Run the `Main.java` class to test the project features.

---

## Logs

Below is a sample of execution logs produced during the project's execution:

```
Connected to PersonalizedFitness database.
Connected to database: PersonalizedFitness
Users Collection: PersonalizedFitness.Users
TrainingPlans Collection: PersonalizedFitness.TrainingPlans

Reading User with UserID 117:
User Found: {"_id": {"$oid": "678c354ad1ef3d681783b8c6"}, "UserID": 117, "Name": "Daniel", "Age": 21, "TrainingPlanID": 5}

Reading all Training Plans:
{"_id": {"$oid": "678c191907d1422c40bae777"}, "PlanID": 1, "Goal": "Updated Goal 1", "Exercises": [...]}

Updated UserID 101 with new Name: Alice
User with UserID 102 not found. No deletion performed.
Training Plan with PlanID 3 not found. No deletion performed.
```

---

## Contributing

Contributions are welcome! If you’d like to improve or expand the project, feel free to fork this repository, create a feature branch, and submit a pull request.

---


## Author

**ABOU ORM Daniel**  
GitHub: [daniel-ao](https://github.com/daniel-ao)


