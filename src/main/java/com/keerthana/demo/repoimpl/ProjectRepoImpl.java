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

	    @SuppressWarnings("unchecked")
	    @Override
	    public List<Project> getAllProjects() {
	        return entityManager.createQuery("FROM Project").getResultList();
	    }

//	    @Override
//	    public void deleteProject(int projectId) {
//	        Project project = findProjectById(projectId);
//	        if (project != null) {
//	            entityManager.remove(project);
//	        }
//	    }
	    
//	    @Override
//	    public void deleteProject(int projectId) {
//
//	        // Find the project by its ID
//	        Project project = findProjectById(projectId);
//	        if (project != null) {
//	        	String sql = "DELETE FROM project_emp WHERE project_project_id = :projectId";
//	            Query query = entityManager.createNativeQuery(sql);
//	            query.setParameter("projectId", projectId);
//	            query.executeUpdate();
//	        }
//	    }
	    @Override
	  
	    public void deleteProject(int projectId) {
	        // Step 1: Remove the association from the `project_emp` table
	        String sql = "DELETE FROM project_emp WHERE project_project_id = :projectId";
	        Query query = entityManager.createNativeQuery(sql);
	        query.setParameter("projectId", projectId);
	        query.executeUpdate();

	        // Step 2: Update the project status to "Completed"
	        Project project = entityManager.find(Project.class, projectId);
	        if (project != null) {
	            project.setProjectStatus("Completed");
	            entityManager.merge(project); // Save the updated status
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
			 // Create the JPQL query to find projects based on the role
	        String jpql = "SELECT p FROM Project p JOIN p.emp e WHERE e.role = :role";
	        
	        // Create and configure the query
	        Query query = entityManager.createQuery(jpql);
	        query.setParameter("role", role);
	        
	        // Execute the query and return the result list
	        return query.getResultList();
		}

		@Override
		public List<String> getAllRolesInProject() {
			 // Create the JPQL query to find all distinct roles from projects
	        String jpql = "SELECT DISTINCT e.role FROM Project p JOIN p.emp e";
	        
	        // Create and configure the query
	        Query query = entityManager.createQuery(jpql);
	        
	        // Execute the query and return the result list
	        return query.getResultList();
	        }
		
	}


