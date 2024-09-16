package com.keerthana.demo.repo;

import java.util.List;

import com.keerthana.demo.model.LoginEmp;
import com.keerthana.demo.model.Project;

public interface ProjectRepo {

	Project saveProject(Project project);

	Project findProjectById(int projectId);

	List<Project> getAllProjects();

	void deleteProject(int projectId);

	boolean existsById(int projectId);

	List<Project> findProjectsByUserId(int userId);

	List<Project> findProjectsByRole(String role);

	List<String> getAllRolesInProject();

	

}
