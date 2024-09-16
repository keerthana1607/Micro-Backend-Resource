package com.keerthana.demo.repoimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keerthana.demo.model.Project;
import com.keerthana.demo.model.Task;
import com.keerthana.demo.repo.TaskRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TaskRepoImpl implements TaskRepo {
	
	 @Autowired
	    private EntityManager entityManager;


	@Override
	public List<Task> getAllTasks() {
		  return entityManager.createQuery("FROM Task").getResultList();
	}

	@Override
	public Task findTaskById(int taskId) {
		 return entityManager.find(Task.class, taskId);
	}

	@Override
	public Task savetask(Task tasks) {
		   if (tasks.getTaskId() == 0) {
	            entityManager.persist(tasks);
	        } else {
	            entityManager.merge(tasks);
	        }
	        return tasks;
	    }
	

	@Override
	public boolean existsById(int taskId) {
		 return findTaskById(taskId) != null;
	}

	@Override
	public List<Task> findByProjectById(int projectId) {
		   String jpql = "SELECT t FROM Task t WHERE t.project.projectId = :projectId";
	        TypedQuery<Task> query = entityManager.createQuery(jpql, Task.class);
	        query.setParameter("projectId", projectId);
	        return query.getResultList();
	}


}
