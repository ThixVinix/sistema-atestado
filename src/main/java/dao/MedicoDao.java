package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Medico;

public class MedicoDao {
	private static MedicoDao instance;
	protected EntityManager entityManager;

	public static MedicoDao getInstance() {
		if (instance == null) {
			instance = new MedicoDao();
		}

		return instance;
	}

	public MedicoDao() {
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
	public List<Medico> findAll() {
		return entityManager.createQuery("FROM " + Medico.class.getName()).getResultList();
	}

	public void persist(Medico medico) {
		EntityTransaction tx = getEntityManager().getTransaction();

		try {
			tx.begin();
			entityManager.merge(medico);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Medico medico) {
		EntityTransaction tx = getEntityManager().getTransaction();

		try {
			tx.begin();
			entityManager.merge(medico);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Medico medico) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			medico = entityManager.find(Medico.class, medico.getId());
			entityManager.remove(medico);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public Medico getById(final Long id) {
		return entityManager.find(Medico.class, id);
	}
}
