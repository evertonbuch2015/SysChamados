package br.com.buch.sysChamados.service;

import java.util.List;

import br.com.buch.sysChamados.dao.GenericDao;
import br.com.buch.sysChamados.entity.Programa;
import br.com.buch.sysChamados.util.NegocioException;
import br.com.buch.sysChamados.util.PersistenciaException;

public class ProgramaService implements GenericService<Programa>{

	@Override
	public String salvar(Programa entidate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(Programa entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Programa carregarEntidade(Programa entidade) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Programa> buscarTodos() throws Exception {		
		return GenericDao.findAll(Programa.class);
	}

	@Override
	public void consisteAntesEditar(Programa entidade) throws NegocioException {
		// TODO Auto-generated method stub
		
	}


}
