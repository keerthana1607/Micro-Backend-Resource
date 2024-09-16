package com.keerthana.demo.service;

import java.util.List;

import com.keerthana.demo.model.Project;

public interface ProjectService {

	List<Project> getAllProjects();

	Project getProjectById(int projectId);

	Project createProject(Project project);

	Project updateProject(int projectId, Project project);

	boolean deleteProject(int projectId);

	boolean addUserToProject(List<Integer> userList, int projectId);

	List<Project> getProjectsByUserId(int userId);

	List<Project> getProjectsByRole(String role);

	List<String> getAllRoles();

}
