package wsSem.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import wsSem.model.Style;
import wsSem.model.User;

public class UserLogic {
	public List<Style> addStyleToUser(String username, String style_id){
		
		EntityManager em = Persistence.createEntityManagerFactory("FindEvent").createEntityManager();
		em.getTransaction().begin();
		
		Query q1 = em.createQuery("SELECT distinct u FROM User u WHERE u.username=?1", User.class);
		q1.setParameter(1, username);
		Query q2 = em.createQuery("SELECT distinct s FROM Style s WHERE s.id=?1", Style.class);
		q2.setParameter(1, Integer.parseInt(style_id));
		User user = (User) q1.getSingleResult();
		Style style = (Style)q2.getSingleResult();
		user.getStyles().add(style);
		em.getTransaction().commit();
		
		String jpql = "SELECT distinct s FROM Style s join fetch s.users as us WHERE us.username=?1";
		Query q = em.createQuery(jpql, Style.class);
		q.setParameter(1, username);
		List<Style> styles = q.getResultList();
		
		//em.close();
		return styles;
	}
	
	public List<Style> deleteUserStyle(String username, String style_id){
		EntityManager em = Persistence.createEntityManagerFactory("FindEvent").createEntityManager();
		em.getTransaction().begin();
		Query q1 = em.createQuery("SELECT distinct u FROM User u WHERE u.username=?1", User.class);
		q1.setParameter(1, username);
		Query q2 = em.createQuery("SELECT distinct s FROM Style s WHERE s.id=?1", Style.class);
		q2.setParameter(1, Integer.parseInt(style_id));
		User user = (User) q1.getSingleResult();
		Style style = (Style)q2.getSingleResult();
		user.getStyles().remove(style);
		em.getTransaction().commit();

		String jpql = "SELECT distinct s FROM Style s join fetch s.users as us WHERE us.username=?1";
		Query q = em.createQuery(jpql, Style.class);
		q.setParameter(1, username);
		List<Style> styles = q.getResultList();
		
		//em.close();
		return styles;
		
	}
	
}
