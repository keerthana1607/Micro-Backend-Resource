package com.keerthana.demo.repoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keerthana.demo.model.LoginEmp;
import com.keerthana.demo.model.Project;
import com.keerthana.demo.repo.ProjectRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProjectRepoImpl implements ProjectRepo {

	    @Autowired
	    private EntityManager entityManager;

	    @Override
	    public Project saveProject(Project project) {
	        if (project.getProjectId() == 0) {
	            entityManager.persist(project);
	        } else {
	        	project.setProjectStatus("Assigned");
	            entityManager.merge(project);
	        }
	        return project;
	    }

	    @Override
	    public Project findProjectById(int projectId) {
	        return entityManager.find(Project.class, projectId);
	    }

	   
	    @Override
	    public List<Project> getAllProjects() {
	        return entityManager.createQuery("FROM Project").getResultList();
	    }


	    @Override
	  
	    public void deleteProject(int projectId) {
	        
	        String sql = "DELETE FROM project_emp WHERE project_project_id = :projectId";
	        Query query = entityManager.createNativeQuery(sql);
	        query.setParameter("projectId", projectId);
	        query.executeUpdate();

	       
	        Project project = entityManager.find(Project.class, projectId);
	        if (project != null) {
	            project.setProjectStatus("Completed");
	            entityManager.merge(project); 
	        }
	    }

	    
	   

	    @Override
	    public boolean existsById(int projectId) {
	        return findProjectById(projectId) != null;
	    }

	    public List<Project> findProjectsByUserId(int userId) {
	        Query query = entityManager.createQuery("SELECT p FROM Project p JOIN p.emp e WHERE e.userId = :userId");
	        query.setParameter("userId", userId);
	        return query.getResultList();
	    }

		@Override
		public List<Project> findProjectsByRole(String role) {
			
	        String jpql = "SELECT p FROM Project p JOIN p.emp e WHERE e.role = :role";
	        
	       
	        Query query = entityManager.createQuery(jpql);
	        query.setParameter("role", role);
	        
	        
	        return query.getResultList();
		}

		@Override
		public List<String> getAllRolesInProject() {
			 
	        String jpql = "SELECT DISTINCT e.role FROM Project p JOIN p.emp e";
	        
	        
	        Query query = entityManager.createQuery(jpql);
	        
	        
	        return query.getResultList();
	        }
		
	}


