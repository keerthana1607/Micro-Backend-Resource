package com.keerthana.demo.repoimpl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keerthana.demo.model.LoginEmp;

import com.keerthana.demo.repo.LoginEmpRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class LoginEmpRepoImpl implements LoginEmpRepo {
	
	

	@Autowired
	EntityManager entityMan;

	@Override
	public LoginEmp insertUser(LoginEmp newUser) {
		entityMan.merge(newUser);
		return newUser;
	}

	@Override
	public LoginEmp updateUser(LoginEmp user) {
		entityMan.merge(user);
		return user;
	}

	
	@Override
	public List<LoginEmp> getUsers() {
		return entityMan.createQuery("from LoginEmp").getResultList();

	}

	@Override
	public LoginEmp userfind(int userId) {
		return entityMan.find(LoginEmp.class, userId);

	}

	
	@Override
	public List<Integer> getUserIdlist() {
		Query q = entityMan.createQuery("SELECT c.userId FROM LoginEmp c");
		return q.getResultList();

	}

	
	public List<LoginEmp> searchUser(String userName) {
		Query qry2 = entityMan.createQuery("from LoginEmp u where u.userName=?1");
		qry2.setParameter(1, userName);
		return qry2.getResultList();
	}

//		public LoginEmp userLogin(String userName, String userPassword) {
//			Query query1 = entityMan.createQuery("from LoginEmp u where u.userName =?1 and u.userPassword=?2");
//			query1.setParameter(1, userName);
//			query1.setParameter(2, userPassword);
//
//			return (LoginEmp) query1.getSingleResult();
//
//		}

	public String getUserRole(String userName, String userPassword) {
		
		String jpql = "SELECT u.role FROM LoginEmp u WHERE u.userName = :userName AND u.userPassword = :userPassword";
		Query query = entityMan.createQuery(jpql);
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);

		
		try {
			return (String) query.getSingleResult();
		} catch (Exception e) {
			
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginEmp> getUsersIdlist(int userId) {
		Query q = entityMan.createQuery("SELECT c.userId FROM LoginEmp c");
		return q.getResultList();
	}

	@Override
	public List<LoginEmp> getAllUnmappedUsersList() {
		
	    String jpql = "SELECT l FROM LoginEmp l WHERE l.role = 'employee' AND l.userId NOT IN ("
                + "SELECT e.userId FROM Project p JOIN p.emp e)";

    return entityMan.createQuery(jpql, LoginEmp.class).getResultList();

	}
	
	 public Map<String, Object> getUserDetails(String userName, String userPassword) {
	        String sql = "SELECT user_id, role FROM login_emp WHERE user_name = :userName AND user_password = :userPassword";
	        Query query = entityMan.createNativeQuery(sql);
	        query.setParameter("userName", userName);
	        query.setParameter("userPassword", userPassword);

	        try {
	            Object[] result = (Object[]) query.getSingleResult();
	            Map<String, Object> userDetails = new HashMap<>();
	            userDetails.put("userId", result[0]);
	            userDetails.put("role", result[1]);
	            return userDetails;
	        } catch (Exception e) {
	            
	            return null;
	        }
	    }

	@Override
	public List<LoginEmp> getAllUnmappedUsersManagerList() {
		String jpql = "SELECT l FROM LoginEmp l WHERE l.role = 'project manager' AND l.userId NOT IN ("
                + "SELECT e.userId FROM Project p JOIN p.emp e)";

    return entityMan.createQuery(jpql, LoginEmp.class).getResultList();
	}

	

	

}
