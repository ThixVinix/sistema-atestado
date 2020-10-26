package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Atestado;

public class AtestadoDao {
	private static AtestadoDao instance;
	protected EntityManager entityManager;

	public static AtestadoDao getInstance() {
		if (instance == null) {
			instance = new AtestadoDao();
		}

		return instance;
	}

	public AtestadoDao() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-atestado");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<Atestado> findAll() {
		return entityManager.createQuery("FROM " + Atestado.class.getName()).getResultList();
	}

	public void persist(Atestado atestado) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			entityManager.merge(atestado);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Atestado atestado) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			entityManager.merge(atestado);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Atestado atestado) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			atestado = entityManager.find(Atestado.class, atestado.getId());
			entityManager.remove(atestado);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public Atestado getById(final Long id) {
		return entityManager.find(Atestado.class, id);
	}
}
