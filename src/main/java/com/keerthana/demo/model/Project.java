package com.keerthana.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId;

	private String projectName;

	private String description;

	private String startDate;

	private String endDate;

	private String requirementSkills;

	private int members;

	private String projectStatus;


	@OneToMany
	private List<LoginEmp> emp;
	

	public Project() {
		super();
	}


	public Project(int projectId, String projectName, String description, String startDate, String endDate,
			String requirementSkills, int members, String projectStatus, List<LoginEmp> emp) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requirementSkills = requirementSkills;
		this.members = members;
		this.projectStatus = projectStatus;
		this.emp = emp;
	}



	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRequirementSkills() {
		return requirementSkills;
	}

	public void setRequirementSkills(String requirementSkills) {
		this.requirementSkills = requirementSkills;
	}

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public List<LoginEmp> getEmp() {
		return emp;
	}

	public void setEmp(List<LoginEmp> emp) {
		this.emp = emp;
	}

	

}
