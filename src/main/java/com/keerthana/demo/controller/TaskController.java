package com.keerthana.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keerthana.demo.model.Project;
import com.keerthana.demo.model.Task;
import com.keerthana.demo.service.ProjectService;
import com.keerthana.demo.service.TaskService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/task")
public class TaskController {
	
	
	@Autowired
	private TaskService task;
	
	@Autowired
	private ProjectService project; 
	
	
	
	   @GetMapping("/getAll")
	    public ResponseEntity<List<Task>> getAllTasks() {
	        List<Task> tasks = task.getAllTasks();
	        return new ResponseEntity<>(tasks, HttpStatus.OK);
	    }

	    @GetMapping("/getbyId/{id}")
	    public ResponseEntity<Task> getProjectById(@PathVariable("id") int taskId) {
	    	Task tasks = task.getTaskById(taskId);
	        if (tasks != null) {
	            return new ResponseEntity<>(tasks, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping("/create")
	    public ResponseEntity<Task> createTask(@RequestBody Task tasks) {
	    	
	
	        if (tasks.getProject() == null || tasks.getProject().getProjectId() == 0) {
	            return ResponseEntity.badRequest().body(null); 
	        }

	        
	        Task createdTask = task.createTask(tasks);
	        return ResponseEntity.ok(createdTask);
	    }


	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Task> updateTask(@PathVariable("id") int taskId, @RequestBody Task taskRequest) {
	        Task existingTask = task.getTaskById(taskId);
	        if (existingTask != null) {
	            
	            existingTask.setTaskName(taskRequest.getTaskName());
	           

	            
	            if (taskRequest.getProject() != null && taskRequest.getProject().getProjectId() != 0) {
	                Optional<Project> projects = Optional.ofNullable(project.getProjectById(taskRequest.getProject().getProjectId()));
	                if (projects.isPresent()) {
	                    existingTask.setProject(projects.get());
	                }
	            }

	            Task updatedTask = task.updateTask(taskId, existingTask);
	            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }



	    @GetMapping("/taskproject/{projectId}")
	    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable("projectId") int projectId) {
	        List<Task> tasks = task.getTasksByProjectId(projectId);
	        if (tasks.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.ok(tasks);
	    }

}
