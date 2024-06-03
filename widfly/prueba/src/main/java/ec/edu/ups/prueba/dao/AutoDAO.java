package ec.edu.ups.prueba.dao;

import java.util.List;

import ec.edu.ups.prueba.model.Auto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class AutoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert (Auto auto) {
		em.persist(auto);
	}
	
	public void update(Auto auto) {
		em.merge(auto);
	}
	
	public void remove(int codigo) {
		Auto auto = em.find(Auto.class, codigo);
		em.remove(auto);
	}
	
	public Auto read (int codigo) {
		Auto auto = em.find(Auto.class, codigo);
		return auto;
	}
	
	public List<Auto> getAll(){
		String jpql = "SELECT c FROM Auto c";
		Query q = em.createQuery(jpql, Auto.class);
		return q.getResultList();
	}
	
	
}
