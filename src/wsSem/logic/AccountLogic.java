package wsSem.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import wsSem.model.User;

public class AccountLogic {
	public List<User> checkLogin(String username, String password){
		EntityManager em = Persistence.createEntityManagerFactory("FindEvent").createEntityManager();
		Query q = em.createQuery("SELECT distinct u from User u WHERE u.username = ?1 AND u.password = ?2", User.class);
		q.setParameter(1, username);
		q.setParameter(2, password);
		List<User> users = q.getResultList();
		em.close();
		return users;
	}
	
	
}
