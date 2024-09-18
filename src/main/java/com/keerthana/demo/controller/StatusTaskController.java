package com.keerthana.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keerthana.demo.model.LoginEmp;

import com.keerthana.demo.model.StatusTask;
import com.keerthana.demo.model.Task;
import com.keerthana.demo.service.LoginEmpService;
import com.keerthana.demo.service.StatusTaskService;
import com.keerthana.demo.service.TaskService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/status")
public class StatusTaskController {
	
	@Autowired
	private StatusTaskService statusService;
	
	@Autowired
	private LoginEmpService loginservice;
	
	@Autowired
	private TaskService taskservice;
	

	@PostMapping("/create")
	public ResponseEntity<?> createStatus(@RequestBody StatusTask statusTaskRequest) {
	    if (statusTaskRequest.getEmp() == null || statusTaskRequest.getTask() == null) {
	        return ResponseEntity.badRequest().body("Employee or Task information is missing");
	    }

	    int userId = statusTaskRequest.getEmp().getUserId();
	    int taskId = statusTaskRequest.getTask().getTaskId();

	    
	    Optional<LoginEmp> loginEmpOptional = Optional.ofNullable(loginservice.userfind(userId));
	    Optional<Task> taskOptional = Optional.ofNullable(taskservice.getTaskById(taskId));

	    
	    if (!loginEmpOptional.isPresent() || !taskOptional.isPresent()) {
	        return ResponseEntity.badRequest().body("Employee or Task not found");
	    }

	    
	    StatusTask statusTask = new StatusTask();
	    statusTask.setTaskStatus(statusTaskRequest.getTaskStatus());
	    statusTask.setTotalHours(statusTaskRequest.getTotalHours());
	    statusTask.setEmp(loginEmpOptional.get());
	    statusTask.setTask(taskOptional.get());

	  
	    StatusTask createdStatusTask = statusService.createStatusTask(statusTask);

	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("statusId", createdStatusTask.getStatusId());
	    response.put("message", "Status successfully added");

	    return ResponseEntity.ok(response);
	}



    
    @GetMapping("/getAll")
    public ResponseEntity<List<StatusTask>> getAllStatusTasks() {
        List<StatusTask> statusTasks = statusService.getAllStatusTasks();
        return new ResponseEntity<>(statusTasks, HttpStatus.OK);
    }

    
    @GetMapping("/getbyId/{id}")
    public ResponseEntity<StatusTask> getStatusTaskById(@PathVariable("id") int statusId) {
        StatusTask statusTask = statusService.getStatusTaskById(statusId);
        if (statusTask != null) {
            return new ResponseEntity<>(statusTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<StatusTask> updateStatusTask(@PathVariable("id") int statusId, @RequestBody StatusTask statusTask) {
        StatusTask existingStatusTask = statusService.getStatusTaskById(statusId);
        if (existingStatusTask != null) {
            statusTask.setStatusId(statusId); 
            StatusTask updatedStatusTask = statusService.updateStatusTask(statusTask);
            return new ResponseEntity<>(updatedStatusTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStatusTask(@PathVariable("id") int statusId) {
        StatusTask existingStatusTask = statusService.getStatusTaskById(statusId);
        if (existingStatusTask != null) {
            statusService.deleteStatusTask(statusId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<StatusTask>> getStatusTasksByUserId(@PathVariable int userId) {
        List<StatusTask> statusTasks = statusService.getStatusTasksByUserId(userId);
        if (statusTasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statusTasks, HttpStatus.OK);
    }
	 
}
