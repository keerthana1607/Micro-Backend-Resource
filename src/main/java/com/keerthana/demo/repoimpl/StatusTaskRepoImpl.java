package com.keerthana.demo.repoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keerthana.demo.model.StatusTask;
import com.keerthana.demo.repo.StatusTaskRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StatusTaskRepoImpl implements StatusTaskRepo {

	@Autowired
	private EntityManager entityManager;

	@Override
	public StatusTask save(StatusTask statusTask) {
		if (statusTask.getStatusId() == 0) {
			entityManager.persist(statusTask);
		} else {
			entityManager.merge(statusTask);
		}
		return statusTask;
	}

	@Override
	public List<StatusTask> findAll() {
		String jpql = "FROM StatusTask";
		TypedQuery<StatusTask> query = entityManager.createQuery(jpql, StatusTask.class);
		return query.getResultList();
	}

	@Override
	public StatusTask findById(int statusId) {
		return entityManager.find(StatusTask.class, statusId);
	}

	@Override
	public void deleteById(int statusId) {
		StatusTask statusTask = findById(statusId);
		if (statusTask != null) {
			entityManager.remove(statusTask);
		}
	}

	@Override
	public List<StatusTask> findByuserId(int userId) {

		
		String jpql = "SELECT st FROM StatusTask st WHERE st.emp.userId = :userId";
		TypedQuery<StatusTask> query = entityManager.createQuery(jpql, StatusTask.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

}