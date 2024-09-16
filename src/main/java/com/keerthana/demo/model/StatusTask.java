package com.keerthana.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class StatusTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statusId;

	private String taskStatus;

	private int totalHours;

	@ManyToOne
    @JoinColumn(name = "user_id")
    private LoginEmp emp;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
	
	public StatusTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public StatusTask(int statusId, String taskStatus, int totalHours, LoginEmp emp, Task task) {
		super();
		this.statusId = statusId;
		this.taskStatus = taskStatus;
		this.totalHours = totalHours;
		this.emp = emp;
		this.task = task;
	}



	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}



	public LoginEmp getEmp() {
		return emp;
	}



	public void setEmp(LoginEmp emp) {
		this.emp = emp;
	}



	public Task getTask() {
		return task;
	}



	public void setTask(Task task) {
		this.task = task;
	}
	

}