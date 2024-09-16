package com.keerthana.demo.service;

import java.util.List;

import com.keerthana.demo.model.StatusTask;

public interface StatusTaskService {

	StatusTask createStatusTask(StatusTask statusTask);

	List<StatusTask> getAllStatusTasks();

	StatusTask getStatusTaskById(int statusId);

	StatusTask updateStatusTask(StatusTask statusTask);

	void deleteStatusTask(int statusId);

	List<StatusTask> getStatusTasksByUserId(int userId);

}
