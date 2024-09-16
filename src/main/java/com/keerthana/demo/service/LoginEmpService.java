package com.keerthana.demo.service;

import java.util.List;
import java.util.Map;

import com.keerthana.demo.model.LoginEmp;

public interface LoginEmpService {

	

		public LoginEmp insertUser(LoginEmp newUser);

		public LoginEmp updateUser(LoginEmp user);

		public List<LoginEmp> getUsers();

		public LoginEmp userfind(int userId);

		public List<Integer> getUserIdlist();

		public List<LoginEmp> searchUser(String userName);

//		public LoginEmp userLogin(String userName, String userPassword);

		public List<LoginEmp> getAllUsersById(int userId);

		public String getUserRole(String userName, String userPassword);

		public List<LoginEmp> getAllUnmappedUsers();

		

		public Map<String, Object> getUserDetails(String userName, String userPassword);

		public List<LoginEmp> getAllUnmappedUsersManager();

		
	}

	

