package br.com.buch.sysChamados.dao;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Everton buchkorn de Souza
 * 
 */
public abstract class GenericDao {

	/**
	 * Retorna um novo Entity Manager.
	 * 
	 * @return EntityManager
	 */
	public static EntityManager getEntityManager() {
		return JPAUtil.GetInstance().getEntityManager();
	}

	/**
	 * Realiza o RoolBack da transa��o e fecha a mesma.
	 * 
	 * @param EntityManager
	 */
	public static void doRollback(EntityManager em) {
		if (em.getTransaction().isActive() == false) {
			em.getTransaction().begin();
		}
		em.getTransaction().rollback();
	}

	/**
	 * Retornar apenas uma Entidade conforme os parametros recebidos. Entidade
	 * estará com o estado DATACHED.
	 * 
	 * @param String
	 * @param Object...
	 * 
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public static <T> T findOne(String jpql, Object... params) throws Exception {

		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery(jpql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		T entity = null;
		try {
			entity = (T) query.getSingleResult();
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			doRollback(em);
			throw ex;
		} finally {
			em.close();
		}

		return entity;
	}

	/**
	 * Retornar apenas uma Entidade conforme os parametros recebidos. Deve
	 * comitar e encerrar o EntityManager manualmente. Entidades estarão com o
	 * estado MANAGED.
	 * 
	 * @param EntityManager
	 * @param String
	 * @param Object...
	 * 
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findOne(EntityManager em, String jpql, Object... params) throws Exception {

		T entity = null;
		Query query = em.createQuery(jpql);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		entity = (T) query.getSingleResult();
		return entity;
	}

	/**
	 * Retornar uma Lista de Entidades conforme os parametros recebidos.
	 * Entidades estarão com o estado DATACHED.
	 * 
	 * @param String
	 * @param Object...
	 * 
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public static <T> List<T> find(String jpql, Object... params) throws Exception {

		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.clear();

		Query query = em.createQuery(jpql).setHint("org.hibernate.readOnly", true);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		List<T> entities = null;
		try {
			entities = query.getResultList();
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			doRollback(em);
			throw e;
		} finally {
			em.close();
		}
		return entities;
	}

	/**
	 * Retornar uma Lista de Entidades conforme os parametros recebidos. Deve
	 * comitar e encerrar o EntityManager manualmente. Entidades estarão com o
	 * estado MANAGED.
	 * 
	 * @param EntityManager
	 * @param String
	 * @param Object...
	 * 
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> find(EntityManager em, String jpql, Object... params) throws Exception {

		Query query = em.createQuery(jpql).setHint("org.hibernate.readOnly", true);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		List<T> entities = null;
		entities = query.getResultList();

		return entities;
	}

	/**
	 * Recupera uma Entidade apenas. Entidade estará com o estado DATACHED.
	 * 
	 * @param EntityManager
	 *            em
	 * @param id
	 * @return
	 */
	public static <T> T findById(Class<T> aClass, Long id) throws Exception {

		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		T entity = null;
		try {
			entity = (T) em.find(aClass, id);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			doRollback(em);
			throw e;
		} finally {
			em.close();
		}
		return entity;
	}

	/**
	 * Recupera uma Entidade apenas. Entidade estará com o estado MANAGED.
	 * 
	 * @param Class
	 * @param EntityManager
	 * @param Long
	 * 
	 * @return T
	 */
	public static <T> T findById(EntityManager em, Class<T> aClass, Long id) throws Exception {
		T entity = null;
		entity = (T) em.find(aClass, id);
		return entity;
	}

	/**
	 * Metodo para retornar todas as Entidades do banco de dados. Entidades
	 * estarão com o estado DATACHED.
	 * 
	 * @param Class
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public static <T> List<T> findAll(Class<T> aClass) throws Exception {

		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("From " + aClass.getSimpleName()).setHint("org.hibernate.readOnly", true);

		List<T> entities = null;
		try {
			entities = query.getResultList();
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			doRollback(em);
			throw e;
		} finally {
			em.close();
		}

		return entities;
	}

	/**
	 * Metodo para realizar o save do Objeto Entidade
	 * 
	 * @param T
	 * @return T
	 */
	public static <T> T save(T entity) throws Exception {
		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			doRollback(em);
			throw e;
		} finally {
			em.close();
		}
		return entity;
	}

	/**
	 * Metodo para realizar o save do Objeto Entidade Deve comitar e encerrar o
	 * EntityManager manualmente.
	 * 
	 * @param EntityManager
	 * @param T
	 * 
	 * @return T
	 */
	public static <T> T save(EntityManager em, T entity) throws Exception {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.persist(entity);
		return entity;
	}

	/**
	 * Metodo para realizar o update do Objeto Entidade
	 * 
	 * @param T
	 * 
	 * @return T
	 */
	public static <T> T update(T entity) throws Exception {
		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			doRollback(em);
			throw e;
		} finally {
			em.close();
		}
		return entity;
	}

	/**
	 * Metodo para realizar o update do Objeto Entidade Deve comitar e encerrar
	 * o EntityManager manualmente.
	 * 
	 * @param EntityManager
	 * @param T
	 * 
	 * @return T
	 */
	public static <T> T update(EntityManager em, T entity) throws Exception {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.merge(entity);
		return entity;
	}

	/**
	 * Remover um objeto do banco recebendo uma Entidade
	 * 
	 * @param T
	 */
	public static <T> void delete(T entity) throws Exception {
		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();
			T c = em.merge(entity);
			em.remove(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			doRollback(em);
			throw e;
		} finally {
			em.close();
		}
	}

	/**
	 * Metodo para realizar o update do Objeto Entidade Deve comitar e encerrar
	 * o EntityManager manualmente.
	 * 
	 * @param EntityManager
	 * @param T
	 */
	public static <T> void delete(EntityManager em, T entity) throws Exception {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		T c = em.merge(entity);
		em.remove(c);
	}

	/**
	 * Remover um objeto do banco recebendo o id da entidade.
	 * 
	 * @param Class
	 * @param Long
	 */
	public static <T> void delete(Class<T> aClass, Long id) throws Exception {
		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();
			em.remove(em.find(aClass, id));
			em.getTransaction().commit();
		} catch (Exception e) {
			doRollback(em);
			throw e;
		} finally {
			em.close();
		}
	}

	/**
	 * Recupera a quantidade de registros da Entidade no banco de dados
	 * 
	 * @param Class
	 * @return T
	 */
	public static <T> Long count(Class<T> aClass) throws Exception {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select count(c) from " + aClass.getSimpleName() + " c ");
		Long count = (Long) query.getSingleResult();

		em.getTransaction().commit();
		em.close();

		return count;
	}

	/**
	 * Recupera o maior valor de um registro Integer da Entidade.
	 * 
	 * @param campo
	 * @return
	 */
	public static <T> Integer getMaxField(Class<T> aClass, String campo) throws Exception {
		EntityManager em = getEntityManager();
		try {
			Query query = em.createQuery("Select max(:campo) From " + aClass.getSimpleName());
			query.setParameter(":campo", campo);
			Integer num = (Integer) query.getSingleResult();

			return num == null ? 1 : num;
		} catch (java.lang.ClassCastException ex) {
			new PersistenceException("Campo passado por parametro deve ser do tipo Integer.", ex);
			return null;
		} finally {
			em.close();
		}
	}
}
