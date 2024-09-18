package com.keerthana.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.keerthana.demo.model.LoginEmp;
import com.keerthana.demo.model.Project;
import com.keerthana.demo.repo.LoginEmpRepo;
import com.keerthana.demo.repo.ProjectRepo;
import com.keerthana.demo.service.EmailService;
import com.keerthana.demo.service.ProjectService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepo projectRepo;

	@Autowired
	private LoginEmpRepo loginrepo;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailService emailService;

	@Override
	public List<Project> getAllProjects() {
		return projectRepo.getAllProjects(); 
	}

	@Override
	public Project getProjectById(int projectId) {
		return projectRepo.findProjectById(projectId); 
	}

	@Override
	public Project createProject(Project project) {
		return projectRepo.saveProject(project); 
	}

	@Override
	public Project updateProject(int projectId, Project project) {
		if (projectRepo.existsById(projectId)) {
			project.setProjectId(projectId);
			return projectRepo.saveProject(project); 
		}
		return null;
	}

	@Override
	public boolean deleteProject(int projectId) {
		if (projectRepo.existsById(projectId)) {
			projectRepo.deleteProject(projectId); 
			return true;
		}
		return false;
	}

	public boolean addUserToProject(List<Integer> userId, int projectId) {
		List<Integer> userIdList = userId;
		
		Project project = projectRepo.findProjectById(projectId);
		
		userIdList.stream().forEach(userid -> {
			LoginEmp user = loginrepo.userfind(userid);
			project.getEmp().add(user);
			emailService.sendMail(user.getUserEmail(), user.getUserName());

		});
		projectRepo.saveProject(project);

		return true;
	}

	@Override
	public List<Project> getProjectsByUserId(int userId) {
		return projectRepo.findProjectsByUserId(userId);
	}

	@Override
	public List<Project> getProjectsByRole(String role) {
		return projectRepo.findProjectsByRole(role);
	}

	@Override
	public List<String> getAllRoles() {
		return projectRepo.getAllRolesInProject();
	}

}
