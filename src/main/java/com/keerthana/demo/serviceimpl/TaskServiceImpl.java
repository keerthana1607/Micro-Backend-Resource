package com.keerthana.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keerthana.demo.model.Project;
import com.keerthana.demo.model.Task;
import com.keerthana.demo.repo.ProjectRepo;
import com.keerthana.demo.repo.TaskRepo;
import com.keerthana.demo.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepo repo;
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Override
	public List<Task> getAllTasks() {
		  return repo.getAllTasks();
	}

	@Override
	public Task getTaskById(int taskId) {
		 return repo.findTaskById(taskId);
	}

	@Override
	public Task createTask(Task tasks) {
		 return repo.savetask(tasks);
	}

//	@Override
//	public Task updateTask(int taskId, Task tasks) {
//		if (repo.existsById(taskId)) {
//			tasks.setTaskId(taskId);
//            return repo.savetask(tasks); 
//        }
//        return null;
//    }
	
	 public Task updateTask(int taskId, Task taskRequest) {
	        if (repo.existsById(taskId)) {
	            Task existingTask = repo.findTaskById(taskId);
	            existingTask.setTaskName(taskRequest.getTaskName());
	           

	            
	            if (taskRequest.getProject() != null && taskRequest.getProject().getProjectId() != 0) {
	                Optional<Project> project = Optional.ofNullable(projectRepo.findProjectById(taskRequest.getProject().getProjectId()));
	                if (project.isPresent()) {
	                    existingTask.setProject(project.get());
	                }
	            }

	            return repo.savetask(existingTask);
	        }
	        return null;
	    }
	@Override
	public List<Task> getTasksByProjectId(int projectId) {
		 return repo.findByProjectById(projectId);
	}

	}


