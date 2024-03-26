package com.masai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.masai.dao.TaskRepository;
import com.masai.entities.Task;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SpringBootAppApplicationTests {

	public static double marks = 1;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Test
	@Order(2)
	@DisplayName("post: create new task when passed valid details")
	void testCreateTask_whenPassedValidTask_thenReturnTaskResponseWithStatusOk() throws Exception {
		// Arrange
        Map<String, String> storeTask = new LinkedHashMap<>();
        storeTask.put("id", "4");
        storeTask.put("title", "Task 4");
        storeTask.put("description", "Description for Task 4");
        storeTask.put("status", "Pending");
        
        Map<String, String> passTask = new LinkedHashMap<>();
        passTask.put("title", "Task 4");
        passTask.put("description", "Description for Task 4");
        passTask.put("status", "Pending");
        
        List<Task> beforePostTasks = taskRepository.findAll();
        
        // Act
        Object savedTask = testRestTemplate.postForObject("/tasks", passTask, Object.class);
        
        // Assert
        assertNotNull(savedTask,
        		() -> "Failure message: Retrive object should not be null");
        assertEquals(storeTask.toString(), savedTask.toString(),
        		() -> "Failure message: Retrive object does not matches with the original object");
        
        // Verify
        assertEquals(beforePostTasks.size()+1, taskRepository.findAll().size(),
        		() -> "Failure message: Retrive object does not matches with the original object");
        marks++;
	}
	
	@Test
	@Order(2)
	@DisplayName("post: create new task when invalid details")
	void testCreateTask_whenPassedInvalidTask_thenResponseWithBadRequest() throws Exception {
		// Arrange
        Map<String, String> passTask = new LinkedHashMap<>();
        passTask.put("title", "");       
        passTask.put("description", "");
        passTask.put("status", "");
        
        // Act
        ResponseEntity<Task> re = testRestTemplate.postForEntity("/tasks", passTask, Task.class);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode(),
        		() -> "Failure message: Expected Bad Request 400 status code but it something else");
        marks++;
	}
	
	@Test
	@Order(3)
	@DisplayName("put: Update existing task when passed valid details")
	void testUpdateTask_whenTaskExists_thenResponseWithUpdatedTaskAndStatusOk() throws Exception {
		// Arrange        
        Map<String, String> passTask = new LinkedHashMap<>();
        passTask.put("title", "Task 1 updated");
        passTask.put("description", "Description for Task 4 updated");
        passTask.put("status", "Completed");
        
        HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(passTask, headers);

	    // Act
	    ResponseEntity<Void> re = testRestTemplate.exchange("/tasks/1", HttpMethod.PUT, httpEntity, Void.class);
        
	    // Assert
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode(),
        		() -> "Failure message: Expected No Content 204 status code but it something else");
        marks++;
	}
	
	@Test
	@Order(3)
	@DisplayName("put: Update existing task when passed invalid details")
	void testUpdateTask_whenTaskExistsPassInvalidDetails_thenResponseWithBadRequest() throws Exception {
		// Arrange        
        Map<String, String> passTask = new LinkedHashMap<>();
        passTask.put("title", "Task 1 updated");
        passTask.put("description", "");
        passTask.put("status", "Completed");
        
        HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(passTask, headers);

	    // Act
	    ResponseEntity<Void> re = testRestTemplate.exchange("/tasks/1", HttpMethod.PUT, httpEntity, Void.class);
        
	    // Assert
        assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode(),
        		() -> "Failure message: Expected Bad Request 400 status code but it something else");
        marks++;
	}
	
	@Test
	@Order(1)
	@DisplayName("get: get all existing task")
	void testGetTasks_whenTaskExists_thenReturnTasksResponseWithStatusOk() throws Exception {
		// Arrange        
        Map<String, String> task1 = new LinkedHashMap<>();
        task1.put("id", "1");
        task1.put("title", "Task 1");
        task1.put("description", "Description for Task 1");
        task1.put("status", "Pending");

        Map<String, String> task2 = new LinkedHashMap<>();
        task2.put("id", "2");
        task2.put("title", "Task 2");
        task2.put("description", "Description for Task 2");
        task2.put("status", "In Progress");

        Map<String, String> task3 = new LinkedHashMap<>();
        task3.put("id", "3");
        task3.put("title", "Task 3");
        task3.put("description", "Description for Task 3");
        task3.put("status", "Completed");
        
        List<Map<String, String>> presentTasks = Arrays.asList(task1, task2, task3);
        
        // Act
        Object tasks = testRestTemplate.getForObject("/tasks", Object.class);
        ResponseEntity<List<Task>> re = testRestTemplate.exchange("/tasks", HttpMethod.GET, null, new ParameterizedTypeReference<List<Task>>() {});
        
        // Assert
        assertNotNull(tasks,
        		() -> "Failure message: Retrive object should be null");
        assertEquals(presentTasks.toString(), tasks.toString(),
        		() -> "Failure message: Retrive object does not matches with the original object");
        assertEquals(HttpStatus.OK, re.getStatusCode(),
        		() -> "Failure message: Expected OK 200 status code but it something else");
        marks++;
	}
	
	@Test
	@Order(5)
	@DisplayName("get: get all task from empty table")
	void testGetTasks_whenTaskDoesNotExists_thenReturnTasksResponseWithStatusOk() throws Exception {
		// Arrange
		taskRepository.deleteAll();
		
		// Act
        ResponseEntity<List<Task>> re = testRestTemplate.exchange("/tasks", HttpMethod.GET, null, new ParameterizedTypeReference<List<Task>>() {});
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode(),
        		() -> "Failure message: Expected Not Found 404 status code but its something else");
        marks++;
	}
	@Test
	@Order(1)
	@DisplayName("get: get existing task by task id")
	void testGetTaskById_whenTaskExists_ReturnTaskResponseWithStatusOk() throws Exception {
		// Arrange
        Map<String, String> task1 = new LinkedHashMap<>();
        task1.put("id", "2");
        task1.put("title", "Task 2");
        task1.put("description", "Description for Task 2");
        task1.put("status", "In Progress");
        
        // Act
        Object tasks = testRestTemplate.getForObject("/tasks/2", Object.class);
        ResponseEntity<Task> re = testRestTemplate.getForEntity("/tasks/2", Task.class);
        
        // Assert
        assertNotNull(tasks,
        		() -> "Failure message: Retrive object should be null");
        assertEquals(task1.toString(), tasks.toString(),
        		() -> "Failure message: Retrive object does not matches with the original object");
        assertEquals(HttpStatus.OK, re.getStatusCode(),
        		() -> "Failure message: Expected OK 200 status code but it something else");
        marks++;
	}
	
	@Test
	@Order(1)
	@DisplayName("get: get non existing task by task id")
	void testGetTaskById_whenTaskExists_thenResponseWithStatusNotFound() throws Exception {
		// Arrange and Act
		ResponseEntity<Task> re = testRestTemplate.exchange("/tasks/-1", HttpMethod.GET, null, Task.class);
        
        // Assert
		assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode(),
        		() -> "Failure message: Expected Not Found 404 status code but it something else");
        marks++;
	}
	
	@Test
	@Order(4)
	@DisplayName("delete: delete existing task")
	void testDeleteTask_whenDeleteExistingTask_thenResponseWithStatusNoContent() throws Exception {
		// Arrange and Act
		ResponseEntity<Void> re = testRestTemplate.exchange("/tasks/1", HttpMethod.DELETE, null, Void.class);
        
        // Assert
		assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode(),
        		() -> "Failure message: Expected No Content 204 status code but it something else");
        
		// Verify
		Boolean status = taskRepository.existsById((long) 1);
		assertFalse(status,
				() -> "Failure message: Record wasn't deleted from the database");
        marks++;
	}
	
	@Test
	@Order(4)
	@DisplayName("delete: delete non existing task")
	void testDeleteTask_whenDeleteNonExistingTask_thenResponseWithStatusNotFound() throws Exception {
		// Arrange and Act
		ResponseEntity<Void> re = testRestTemplate.exchange("/tasks/-1", HttpMethod.DELETE, null, Void.class);
        
        // Assert
		assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode(),
        		() -> "Failure message: Expected Not Found 404 status code but it something else");
        marks++;
	}
	
	@Test
    @Order(12)
    void buildScore(){
        System.out.println("[MARKS] marks is " + marks);
    }
}