package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dto.RiscosComunsDTO;
import model.Risco;

public class RiscoDao {
	private static RiscoDao instance;
	protected EntityManager entityManager;

	public static RiscoDao getInstance() {
		if (instance == null) {
			instance = new RiscoDao();
		}

		return instance;
	}

	public RiscoDao() {
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
	public List<Risco> findAll() {
		return entityManager.createQuery("FROM " + Risco.class.getName()).getResultList();
	}

	public void persist(Risco risco) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			entityManager.persist(risco);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Risco risco) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			entityManager.merge(risco);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Risco risco) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			risco = entityManager.find(Risco.class, risco.getId());
			entityManager.remove(risco);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public List<RiscosComunsDTO> riscosMaisComuns() {
		String consulta = "SELECT new dto.RiscosComunsDTO(r.nome, COUNT(r)) FROM Risco r INNER JOIN r.atestado ar GROUP BY r.nome";
		TypedQuery<RiscosComunsDTO> query = entityManager.createQuery(consulta, RiscosComunsDTO.class);
		return query.getResultList();
	}

	public Risco getById(final Long id) {
		return entityManager.find(Risco.class, id);
	}

}
