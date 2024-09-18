package com.keerthana.demo.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keerthana.demo.model.LoginEmp;
import com.keerthana.demo.repo.LoginEmpRepo;
import com.keerthana.demo.service.LoginEmpService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class LoginEmpServiceImpl implements LoginEmpService {


		@Autowired
		LoginEmpRepo userdao;

		
		public LoginEmp insertUser(LoginEmp newUser) {
			return userdao.insertUser(newUser);

		}

		public LoginEmp updateUser(LoginEmp user) {
			return userdao.updateUser(user);
		}

		
		public List<LoginEmp> getUsers() {
			return userdao.getUsers();
		}

		
		public LoginEmp userfind(int userId) {
			return userdao.userfind(userId);
		}

	
		public List<Integer> getUserIdlist() {
			return userdao.getUserIdlist();
		}

		
		public List<LoginEmp> searchUser(String userName) {
			return userdao.searchUser(userName);
		}

		


		
		public String getUserRole(String userName, String userPassword) {
	        return userdao.getUserRole(userName, userPassword);
	    }
	
		public List<LoginEmp> getAllUsersById(int userId) {
			return userdao.getUsersIdlist(userId);
		}

		@Override
		public List<LoginEmp> getAllUnmappedUsers() {
			return userdao.getAllUnmappedUsersList();
		}

		@Override
		public Map<String, Object> getUserDetails(String userName, String userPassword) {
			 return userdao.getUserDetails(userName,userPassword);
		}

		@Override
		public List<LoginEmp> getAllUnmappedUsersManager() {
			return userdao.getAllUnmappedUsersManagerList();
		}
		
		
	}

	

