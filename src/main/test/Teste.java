
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.buch.sysChamados.dao.GenericDao;
import br.com.buch.sysChamados.dao.JPAUtil;
import br.com.buch.sysChamados.entity.Programa;
import br.com.buch.sysChamados.entity.Projeto;
import br.com.buch.sysChamados.entity.Servico;
import br.com.buch.sysChamados.service.ProjetoService;

public class Teste {

	public static void main(String[] args) {
		//inserir2();
		//buscar();
		
		ProjetoService e = new ProjetoService();
		try {
			e.buscarTodos();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void inserir2() {
		EntityManager em = JPAUtil.GetInstance().getEntityManager();
		try {

			em.getTransaction().begin();
			
			Programa entity = new Programa();
			entity.setCodigo(1590);
			entity.setDescricao("teste1");
			entity.setMenu("Teste1");
			entity.setProgramaAuxiliar('N');
			entity.setVersao("1.8.22.15");

			GenericDao.save(em, entity);

			Programa entity2 = new Programa();
			entity2.setCodigo(1900);
			entity2.setDescricao("teste2");
			entity2.setMenu("Teste2");
			entity2.setProgramaAuxiliar('N');
			entity2.setVersao("1.8.23.15");

			GenericDao.save(em, entity2);
			
			
			Servico servico = new Servico();
			entity2.setCodigo(1102);
			entity2.setDescricao("teste5");
			
			GenericDao.save(em, servico);
			
			
			//GenericDao.doRollback(em);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			GenericDao.doRollback(em);
			e.printStackTrace();
		}
	}

	public static void inserir() {
		EntityManager em = JPAUtil.GetInstance().getEntityManager();

		Programa entity = new Programa();

		entity.setCodigo(100);
		entity.setDescricao("teste");
		entity.setMenu("Teste");
		entity.setProgramaAuxiliar('N');
		entity.setVersao("1.8.22.12");

		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public static void buscarId() {
		EntityManager em = JPAUtil.GetInstance().getEntityManager();
		em.getTransaction().begin();

		Projeto entity = null;
		try {
			entity = em.find(Projeto.class, 2l);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	
	
	public static void buscar() {
		EntityManager em = JPAUtil.GetInstance().getEntityManager();
		Query query = em.createQuery("From Projeto").setHint("org.hibernate.readOnly", true);

		List<Projeto> entities = null;
		try {
			entities = query.getResultList();
			System.out.println(entities.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
