package com.keerthana.demo.repo;

import java.util.List;
import java.util.Map;

import com.keerthana.demo.model.LoginEmp;


public interface LoginEmpRepo {


	public LoginEmp insertUser(LoginEmp newUser);

	public LoginEmp updateUser(LoginEmp user);

	public List<LoginEmp> getUsers();

	public LoginEmp userfind(int userId);

	public List<Integer> getUserIdlist();

	public List<LoginEmp> searchUser(String userName);

	

	public List<LoginEmp> getUsersIdlist(int userId);

	public String getUserRole(String userName, String userPassword);

	public List<LoginEmp> getAllUnmappedUsersList();

	 public Map<String, Object> getUserDetails(String userName, String userPassword);

	public List<LoginEmp> getAllUnmappedUsersManagerList();
	
	
	
}
