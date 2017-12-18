package br.com.buch.sysChamados.service;

import java.util.List;

import br.com.buch.sysChamados.dao.GenericDao;
import br.com.buch.sysChamados.entity.Programa;
import br.com.buch.sysChamados.util.NegocioException;
import br.com.buch.sysChamados.util.PersistenciaException;
import br.com.buch.sysChamados.util.UtilErros;

public class ProgramaService implements GenericService<Programa> {

	private static final String BUSCAR_MENUS = "Select distinct p.menu From Programa p";
	
	@Override
	public String salvar(Programa entidate) throws Exception {
		if (entidate.getId() == null) {

			try {
				GenericDao.save(entidate);
				return "Programa Cadastrado com Sucesso!";
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(
						"Ocorreu uma exceção ao inserir o Programa!" + " \nErro: " + UtilErros.getMensagemErro(e));
			}
		} else {

			try {
				GenericDao.update(entidate);
				return "Programa Alterado com Sucesso!";
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(
						"Ocorreu uma exceção ao alterar o Programa!" + " \nErro: " + UtilErros.getMensagemErro(e));
			}
		}
	}

	@Override
	public String excluir(Programa entidade) throws Exception {
		try {
			GenericDao.delete(entidade);
			return "Programa excluido com sucesso";
		} catch (Exception ex) {
			ex.printStackTrace();
			if (ex.getCause().toString().contains("ConstraintViolationException")) {
				throw new PersistenciaException(
						"Programa não pode ser excluido pois existem registros vinculados a ele!");
			} else {
				throw new PersistenciaException(
						"Ocorreu uma exceção ao Excluir o Programa!" + " \nErro: " + UtilErros.getMensagemErro(ex));
			}
		}
	}

	@Override
	public Programa carregarEntidade(Programa entidade) throws PersistenciaException {
		try {
			return GenericDao.findById(Programa.class, entidade.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(
					"Ocorreu uma exceção ao buscar os dados do Programa!" + " \nErro: " + UtilErros.getMensagemErro(e));
		}
	}

	@Override
	public List<Programa> buscarTodos() throws Exception {
		return GenericDao.findAll(Programa.class);
	}

	@Override
	public void consisteAntesEditar(Programa entidade) throws NegocioException {
		throw new NegocioException("Erro");
	}

	
	public List<String> buscarMenus()throws Exception{
		return GenericDao.find(BUSCAR_MENUS);
	}
}
