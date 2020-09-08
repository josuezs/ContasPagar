package br.com.deliverit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractDAO {

	private static final Logger log = LogManager.getLogger(AbstractDAO.class);

	@PersistenceContext(unitName = "contasPagarPU")
	protected EntityManager em;

	public <T> T save(T e) throws Exception {
		em.persist(e);
		return e; 
	}
	
	protected <T> T singleResult(Query q) {
		List<T> list = q.getResultList();
		return (list != null && !list.isEmpty() ? list.get(0) : null);
	}

	protected <T> List<T> resultList(Query q) {
		List<T> list = q.getResultList();
		return (list != null && !list.isEmpty() ? list : null);
	}

	public <T> List<T> findAll(Class<T> entityClass) {
		log.info("Buscando todos os registros de: " + entityClass.getSimpleName());
		return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e").getResultList();
	}
	
}
