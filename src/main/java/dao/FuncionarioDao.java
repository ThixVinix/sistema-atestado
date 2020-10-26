package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Funcionario;

public class FuncionarioDao {
	private static FuncionarioDao instance;
	protected EntityManager entityManager;

	public static FuncionarioDao getInstance() {
		if (instance == null) {
			instance = new FuncionarioDao();
		}

		return instance;
	}

	public FuncionarioDao() {
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
	public List<Funcionario> findAll() {
		return entityManager.createQuery("FROM " + Funcionario.class.getName()).getResultList();
	}

	public void persist(Funcionario funcionario) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			entityManager.merge(funcionario);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Funcionario funcionario) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			entityManager.merge(funcionario);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Funcionario funcionario) {
		EntityTransaction tx = getEntityManager().getTransaction();

		try {

			tx.begin();
			funcionario = entityManager.find(Funcionario.class, funcionario.getId());
			entityManager.remove(funcionario);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public Funcionario getById(final Long id) {
		return entityManager.find(Funcionario.class, id);
	}
}
