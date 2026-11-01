# Contract and Employee Leave Management Backend


- **Name:** Shalom ISHIMWE
- **Student ID:** 28766
- **Project Title:** Contract and Employee Leave Management Backend

## 1. About the Project

This project is a backend system developed to help an organization manage employee contracts and leave requests.

The system allows users to manage employees, departments, HR officers, contract types, leave requests and leave deductions. It also provides features for searching records, checking leave request statuses, applying business rules and generating reports.

Through this project, I applied the concepts learned in Web Technology, including Java programming, Spring Boot, database management and REST API development.

## 2. Technologies Used

The following technologies and tools were used to develop the project:

- **Java 25:** Used to write the application code.
- **Spring Boot 4.1.2:** Used to build the backend application.
- **Spring Data JPA:** Used to connect the application to the database and manage data.
- **PostgreSQL 18:** Used to store and manage the project data.
- **pgAdmin 4:** Used to create and manage the PostgreSQL database.
- **Maven:** Used to manage project dependencies and run the application.
- **Postman:** Used to test the API endpoints.

## 3. Main Features

The system provides the following features:

- Creating, viewing, updating and deleting contract types, departments, HR officers, employees and leave requests.
- Searching for employees by last name.
- Finding leave requests by status and date range.
- Finding HR officers based on their contract types.
- Checking departments that have no HR officer assigned.
- Managing contract types assigned to HR officers.
- Changing the departments assigned to HR officers.
- Applying rules when creating and updating leave requests.
- Recording, viewing and deleting leave deductions.
- Generating statistics about leave requests.
- Cancelling eligible leave requests for a specific date.

## 4. Database Setup

The project uses PostgreSQL to store its data. I used pgAdmin 4 to create and manage the database.

To prepare the database:

1. Open pgAdmin 4.
2. Connect to your PostgreSQL server.
3. Right-click **Databases** and select **Create → Database**.
4. Enter the following database name:

   `contactand_leave_management`

5. Click **Save**.

After creating the database, open the project in Visual Studio Code and navigate to:

`src/main/resources/application.properties`

Check the database connection settings and update the PostgreSQL username and password to match your local PostgreSQL installation.

The database connection URL is:

`jdbc:postgresql://localhost:5432/contactand_leave_management`

## 5. Running the Application

To run the project on Windows:

1. Make sure PostgreSQL is running.
2. Open the project folder in Visual Studio Code.
3. Open the terminal from the project folder.
4. Run the following command:

  
   .\mvnw.cmd spring-boot:run
  

5. Wait for the application to finish starting.

Once the application starts successfully, the backend will be available at:

`http://localhost:8082`

The API endpoints can now be accessed and tested.

## 6. Testing the APIs with Postman

I used Postman to test the different API endpoints and check that the system responds correctly.

The Postman collection is included in the root folder of this repository:

`contractand-employee-leave-management-midterm.postman_collection.json`

To use the collection:

1. Open Postman.
2. Click **Import**.
3. Select the JSON collection file from the project folder.
4. Open the imported collection.
5. Make sure the Spring Boot application is running.
6. Select the request you want to test and click **Send**.

The collection is organized into folders covering Parts A to F of the assignment.

## 7. GitHub Repository

The complete source code and Postman collection are available here:

[Contract and Employee Leave Management Backend](https://github.com/0608shalom/contractand-employee-leave-management-backend)

