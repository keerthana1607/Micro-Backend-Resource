package com.keerthana.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keerthana.demo.model.LoginEmp;
import com.keerthana.demo.service.LoginEmpService;
import com.keerthana.demo.serviceimpl.EmailServiceimpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class LoginEmpController {


		@Autowired
		LoginEmpService userservice;

		// to insert the data
//		@PostMapping("/doUserInsert")
//		public LoginEmp insertUser(@RequestBody LoginEmp newUser) {
//			newUser.setUserPassword("12345");
//			return userservice.insertUser(newUser);
//
//		}
		
		@Autowired
	    private EmailServiceimpl emailService;

	    @PostMapping("/doUserInsert")
	    public LoginEmp insertUser(@RequestBody LoginEmp newUser) {
	        newUser.setUserPassword("12345");
	        LoginEmp insertedUser = userservice.insertUser(newUser);

	        // Send email after inserting the user
	        if (insertedUser != null) {
	            String subject = "Welcome to the Company";
	            String text = "Hello " + insertedUser.getUserName() + ",\n\n"
	                        + "Your account has been created successfully. You can login with your Username "+insertedUser.getUserName()+" & temporary password is 12345.\n\n"
	                        + "Best Regards,\nCompany Team";

	            emailService.sendSimpleMessage(insertedUser.getUserEmail(), subject, text);
	        }

	        return insertedUser;
	    }
	 
		@PutMapping("/updateUser")
		public LoginEmp updateUser(@RequestBody LoginEmp user) {
			
			return userservice.updateUser(user);
		}

		// to get all the data from the database
		@GetMapping("/getAllUserList")
		public List<LoginEmp> getUsers() {
			return userservice.getUsers();
		}

		// get by id
		@GetMapping("/GetByUserId/{userId}")
		public LoginEmp find(@PathVariable("userId") int userId) {
			return userservice.userfind(userId);
		}

//		@GetMapping("/loginUser/{userName}/{userPassword}")
//		public LoginEmp loginUser(@PathVariable("userName") String userName,
//				@PathVariable("userPassword") String userPassword) {
//
//			return userservice.userLogin(userName, userPassword);
//
//		}
		
//		 @GetMapping("/loginUser/{userName}/{userPassword}")
//		    public ResponseEntity<String> getUserRole(@PathVariable("userName") String userName,
//		                                               @PathVariable("userPassword") String userPassword) {
//		        String role = userservice.getUserRole(userName, userPassword);
//		        if (role != null) {
//		            return ResponseEntity.ok(role); // Return the role
//		        } else {
//		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Handle unauthorized access
//		        }
//		    }
//		

		@GetMapping("/getAllUsersById")
		public List<LoginEmp> getAllUserListById(int userId) {
			return userservice.getAllUsersById(userId);
		}
		
		@GetMapping("/loginUser/{userName}/{userPassword}")
		public ResponseEntity<Map<String, Object>> getUserRole(@PathVariable("userName") String userName,
		                                                        @PathVariable("userPassword") String userPassword) {
		    Map<String, Object> userDetails = userservice.getUserDetails(userName, userPassword);
		    if (userDetails != null) {
		        return ResponseEntity.ok(userDetails); // Return userId and role
		    } else {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Handle unauthorized access
		    }
		}

		
		@GetMapping("/getAllUnmappedUser")
		public List<LoginEmp> getAllUnmappedUser(){
			return userservice.getAllUnmappedUsers();
		}
		
		@GetMapping("/getAllUnmappedUserManager")
		public List<LoginEmp> getAllUnmappedUserManager(){
			return userservice.getAllUnmappedUsersManager();
		}
		
		
	}

	
	

