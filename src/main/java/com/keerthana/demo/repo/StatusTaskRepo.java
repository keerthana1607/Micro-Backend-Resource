package com.keerthana.demo.repo;

import java.util.List;

import com.keerthana.demo.model.StatusTask;

public interface StatusTaskRepo {

	StatusTask save(StatusTask statusTask);

	List<StatusTask> findAll();

	StatusTask findById(int statusId);

	void deleteById(int statusId);

	List<StatusTask> findByuserId(int userId);

}
