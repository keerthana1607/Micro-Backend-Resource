package com.keerthana.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class LoginEmp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String userName;
	private String userPassword;
	private String userMobile;
	private String userEmail;
	private String userAddress;
	private String role;
	private String skills;
	private String experience;
	private String projectsWorked;
	private String degree;
	private String dob;
	private String state;
	private String bloodGroup;
	private String languages;
	private String gender;

	public LoginEmp() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public LoginEmp(int userId, String userName, String userPassword, String userMobile, String userEmail,
			String userAddress, String role, String skills, String experience, String projectsWorked, String degree,
			String dob, String state, String bloodGroup, String languages, String gender) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userMobile = userMobile;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.role = role;
		this.skills = skills;
		this.experience = experience;
		this.projectsWorked = projectsWorked;
		this.degree = degree;
		this.dob = dob;
		this.state = state;
		this.bloodGroup = bloodGroup;
		this.languages = languages;
		this.gender = gender;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getProjectsWorked() {
		return projectsWorked;
	}

	public void setProjectsWorked(String projectsWorked) {
		this.projectsWorked = projectsWorked;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
}
