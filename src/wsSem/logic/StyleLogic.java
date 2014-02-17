package wsSem.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import wsSem.model.Style;

public class StyleLogic {

	public List<Style> getAllStyles(){
		EntityManager em = Persistence.createEntityManagerFactory("FindEvent").createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM Style s WHERE 1=1");
		//Query q = em.createQuery("SELECT distinct s FROM Style s WHERE 1=1", Style.class); this is better
		List<Style> styles = q.getResultList();
		em.close();
		return styles;
	}

	public List<Style> getStylesAvailableByUserName(String username){
		EntityManager em = Persistence.createEntityManagerFactory("FindEvent").createEntityManager();
		String jpql = "SELECT distinct s FROM Style s "
				+ "WHERE s.id NOT IN "
				+ "("
				+ "SELECT s2.id FROM Style s2 JOIN s2.users as us2 WHERE us2.username=?1"
				+ ")";
		Query q = em.createQuery(jpql, Style.class);
		q.setParameter(1, username);
		List<Style> styles = q.getResultList();
		em.close();
		return styles;
	}

	public List<Style> getStylesByUserName(String username){

		EntityManager em = Persistence.createEntityManagerFactory("FindEvent").createEntityManager();
		String stringResult = "";
		String jpql = "SELECT distinct s FROM Style s join fetch s.users as us WHERE us.username=?1";
		Query q = em.createQuery(jpql, Style.class);
		q.setParameter(1, username);
		List<Style> styles = q.getResultList();
		em.close();
		return styles;
	}

}

