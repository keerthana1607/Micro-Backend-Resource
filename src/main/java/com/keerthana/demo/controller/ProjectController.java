package com.keerthana.demo.controller;

import java.util.Collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keerthana.demo.model.LoginEmp;
import com.keerthana.demo.model.Project;
import com.keerthana.demo.service.LoginEmpService;
import com.keerthana.demo.service.ProjectService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/project")
public class ProjectController {

	
	 @Autowired
	    private ProjectService projectService;
	 
	 @Autowired
	 private LoginEmpService userservice;

	    @GetMapping("/getAll")
	    public ResponseEntity<List<Project>> getAllProjects() {
	        List<Project> projects = projectService.getAllProjects();
	        return new ResponseEntity<>(projects, HttpStatus.OK);
	    }

	    @GetMapping("/getbyId/{id}")
	    public ResponseEntity<Project> getProjectById(@PathVariable("id") int projectId) {
	        Project project = projectService.getProjectById(projectId);
	        if (project != null) {
	            return new ResponseEntity<>(project, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }


	    
	    @PostMapping("/create")
	    public ResponseEntity<Map<String, Integer>> createProject(@RequestBody Map<String, Object> request) {
	        try {
	            
	            Project project = extractProjectFromRequest(request);

	         
	            Object userIdObj = request.get("userId");
	            Integer userId = null;

	            if (userIdObj instanceof Number) {
	                userId = ((Number) userIdObj).intValue();
	            } else if (userIdObj instanceof String) {
	                try {
	                    userId = Integer.parseInt((String) userIdObj);
	                } catch (NumberFormatException e) {
	                    throw new IllegalArgumentException("Invalid data type for userId");
	                }
	            } else {
	                throw new IllegalArgumentException("Invalid data type for userId");
	            }

	            
	            System.out.println("Fetching user with ID: " + userId);

	        
	            LoginEmp user = userservice.userfind(userId);
	            if (user == null) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }

	          
	            project.setEmp(Collections.singletonList(user));
	            
	            if (project.getProjectStatus() == null || project.getProjectStatus().isEmpty()) {
	                project.setProjectStatus("Pending");
	            }

	            
	            Project createdProject = projectService.createProject(project);

	           
	            Map<String, Integer> response = new HashMap<>();
	            response.put("projectId", createdProject.getProjectId());

	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }


	    private Project extractProjectFromRequest(Map<String, Object> request) {
	        Project project = new Project();
	        project.setProjectName((String) request.get("projectName"));
	        project.setDescription((String) request.get("description"));
	        project.setStartDate((String) request.get("startDate"));
	        project.setEndDate((String) request.get("endDate"));
	        project.setRequirementSkills((String) request.get("requirementSkills"));

	       
	        Object membersObj = request.get("members");
	        if (membersObj instanceof Number) {
	            project.setMembers(((Number) membersObj).intValue());
	        } else if (membersObj instanceof String) {
	            try {
	                project.setMembers(Integer.parseInt((String) membersObj));
	            } catch (NumberFormatException e) {
	                throw new IllegalArgumentException("Invalid data type for members");
	            }
	        } else {
	            throw new IllegalArgumentException("Invalid data type for members");
	        }
	        return project;
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProject(@PathVariable("id") int projectId) {
	        boolean isDeleted = projectService.deleteProject(projectId);
	        if (isDeleted) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	    @PutMapping("/update/{projectId}")
		public ResponseEntity<Object> addUsersToProject(@RequestBody List<Integer> userList,
				@PathVariable("projectId") int projectId) {
			if (projectService.addUserToProject(userList, projectId)) {
				return ResponseEntity.ok(null);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
	    
	    
	    @GetMapping("/getByUserId/{userId}")
	    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable("userId") int userId) {
	        List<Project> projects = projectService.getProjectsByUserId(userId);
	        if (!projects.isEmpty()) {
	            return new ResponseEntity<>(projects, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    @GetMapping("/getByRole/{role}")
	    public ResponseEntity<List<Project>> getProjectsByRole(@PathVariable("role") String role) {
	        List<Project> projects = projectService.getProjectsByRole(role);
	        if (!projects.isEmpty()) {
	            return new ResponseEntity<>(projects, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	    @GetMapping("/roles")
	    public ResponseEntity<List<String>> getAllRoles() {
	        List<String> roles = projectService.getAllRoles();
	        if (!roles.isEmpty()) {
	            return new ResponseEntity<>(roles, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	        }
	    }

	
}
