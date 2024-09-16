package com.keerthana.demo.service;

import java.util.List;

import com.keerthana.demo.model.Task;

public interface TaskService {

	List<Task> getAllTasks();

	Task getTaskById(int taskId);

	Task createTask(Task tasks);

	Task updateTask(int taskId, Task tasks);

	List<Task> getTasksByProjectId(int projectId);

}
