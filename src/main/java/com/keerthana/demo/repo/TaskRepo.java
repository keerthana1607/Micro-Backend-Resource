package com.keerthana.demo.repo;

import java.util.List;

import com.keerthana.demo.model.Task;

public interface TaskRepo {

	List<Task> getAllTasks();

	Task findTaskById(int taskId);

	Task savetask(Task tasks);

	boolean existsById(int taskId);

	List<Task> findByProjectById(int projectId);

	

}
