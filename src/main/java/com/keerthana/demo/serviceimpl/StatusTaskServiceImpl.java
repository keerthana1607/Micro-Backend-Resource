package com.keerthana.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keerthana.demo.model.StatusTask;
import com.keerthana.demo.repo.StatusTaskRepo;
import com.keerthana.demo.service.StatusTaskService;

@Service
public class StatusTaskServiceImpl implements StatusTaskService {
	
	 @Autowired
	    private StatusTaskRepo statusTaskRepository;

	    public StatusTask createStatusTask(StatusTask statusTask) {
	        return statusTaskRepository.save(statusTask);
	    }

	    public List<StatusTask> getAllStatusTasks() {
	        return statusTaskRepository.findAll();
	    }

	    public StatusTask getStatusTaskById(int statusId) {
	        return statusTaskRepository.findById(statusId);
	    }

	    public StatusTask updateStatusTask(StatusTask statusTask) {
	        return statusTaskRepository.save(statusTask);
	    }

	    public void deleteStatusTask(int statusId) {
	        statusTaskRepository.deleteById(statusId);
	    }

		@Override
		public List<StatusTask> getStatusTasksByUserId(int userId) {
			return statusTaskRepository.findByuserId(userId);
		}
	}


