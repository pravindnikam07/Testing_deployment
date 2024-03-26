# **App -** TaskMaster

## **Submission Instructions [Please note]**

## **Maximum Marks - 11**

| Sr. No. | Description | Marks |
| --- | --- | --- |
| 1 | Able to submit the app launched without error | 1 |
| 2 | post: "/tasks" -> add new task along with validation  | 2 |
| 3 | put: "/tasks/{taskId}" -> Update task along with validation | 2 |
| 4 | delete: "/tasks/{taskId}" -> delete task | 2 |
| 5 | get: "/tasks/{taskId}" -> get task with taskId | 2 |
| 6 | get: "/tasks" -> get tasks | 2 |
|  | Total | 11 |

### **Objective:**

1. By the end of this problem, you will have a functional Task management application.
2. Able to create restful API endpoints using the spring boot project.
3. Understand how to use Spring Data JPA, and get familiar with Validation, and Exception handling.

### Installation

- Download and unzip the boilerplate and follow the following steps to import it into your IDE.
- Open the IDE(STS) → File → Import → Maven → Existing Maven Projects (double click on it).
- Click on the Browse… button and select the project template → next → finish.

### Boilerplate folder structure

- Please ignore all files except the mentioned one to use.
- Do not change the given folder structure.

```java
SpringBootApp
		├───src
		│   ├───main
		│   │   ├───java/com.masai
		│   │   │   │   Runner.java [Use]
		│   │   │   │   SpringBootAppApplication.java [Use]
		│   │   │   ├───controller
		│   │   │   │       TaskController.java [Use]
		│   │   │   ├───dao
		│   │   │   │       TaskRepository.java [Use]
		│   │   │   ├───entities
		│   │   │   │       Task.java [Use]
		│   │   │   └───service
		│   │   │           TaskService.java [Use]
		│   │   └───resources
		│   │           application.properties [Use]
		│   │           sample-data.sql [Use]
		│   └───test
		│       └───java/com.masai
		│	              SringBootAppApplicationTests.java [ignore]
		└───pom.xml [Do not modify]
```

# Problem Statement: - TaskMaster

## Problem description: -

Implement a simple web application using Spring Boot to create a task management system. The application should allow users to create, update, and delete tasks. Each task should have a unique identifier, title, description, and status (e.g., "Pending," "Completed," "In Progress"). Users should be able to view a list of all tasks and filter them based on status.

## **Requirements**

1. Create a RESTful API endpoint to add a new task.
    - Endpoint: **`POST /tasks`**
    - Request body: JSON object containing task details (title, description, and status).
    - Response: JSON object containing the task details (id, title, description, and status) with HTTP status 201 Created on success. refer to the below response format.
        
        ```json
        {
        	"id": "1"
          "title": "title",
          "description": "task description",
          "status": "Pending"
        }
        ```
        
2. Create a RESTful API endpoint to update an existing task.
    - Endpoint: **`PUT /tasks/{taskId}`**
    - Path variable: **`taskId`** (string) - The task’s unique identifier to be updated.
    - Request body: JSON object containing updated task details (title, description, and status).
    - Response: Empty response with HTTP status 204 No Content on success.
3. Create a RESTful API endpoint to delete a task.
    - Endpoint: **`DELETE /tasks/{taskId}`**
    - Path variable: **`taskId`** (string) - The unique identifier of the task to be deleted.
    - Response: Empty response with HTTP status 204 No Content on success.
4. Create a RESTful API endpoint to retrieve a task by its ID.
    - Endpoint: **`GET /tasks/{taskId}`**
    - Path variable: **`taskId`** (string) - The task’s unique identifier to retrieve.
    - Response: JSON object containing the task details (id, title, description, and status) with HTTP status 200 OK on success.
5. Create a RESTful API endpoint to retrieve all tasks.
    - Endpoint: **`GET /tasks`**
    - Response: JSON array containing all task objects with HTTP status 200 OK on success.
6. Implement input validation for creating and updating tasks to ensure that the provided data is complete and valid.
    - Validate that the **`title`** and **`description`** fields are not empty.
    - Validate that the **`status`** field is one of the predefined values ("Pending," "Completed," "In Progress").
    - Return appropriate error messages and HTTP status 400 Bad Request for invalid input.
7. Implement exception handling to return proper error messages and HTTP status codes for different scenarios.
    - Return HTTP status 404 Not Found when retrieving or deleting a non-existent task.
    - Return HTTP status 400 Bad Request for invalid input during task creation or update.

**Note:** For entity class attributes please refer to the `sample-data.sql` file and choose the appropriate fields and data types.

### Common instructions for pom.xml

- pom.xml file is given along with the boilerplate code.
- Do not make any changes to the existing content of the **`pom.xml`** file.

### Test your application’s methods on your local system.

- From the main method of the Runner class, you can test your application’s methods in your local system. You can retrieve an object from the container and call the methods if you want.

### Check your application for a few sample test cases:

Steps to test your application:

- Run the maven project (refer to the below steps).
    - Right-click on the project → Run As → JUnit test/Maven test.
    refer to the image for more clarity: [**link**](https://drive.google.com/file/d/1jIr8BUPfdoJ-JB8oP2SBJTzJt7boUWZ5/view?usp=sharing)

### General guidelines

- The system on cp.masaischool.com may take between 1-20 minutes for responding.
- So, we request you to read the problem carefully and debug it before itself.
- We also request you not just submit it last minute.
- Try to keep one submission at a time.
- Use the template provided to write your code (Mandatory).
