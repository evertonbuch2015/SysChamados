package br.com.buch.sysChamados.service;

import java.util.List;

import br.com.buch.sysChamados.entity.Projeto;
import br.com.buch.sysChamados.util.NegocioException;
import br.com.buch.sysChamados.util.PersistenciaException;

public class ProjetoService<T extends Projeto> implements GenericService<T>{

	@Override
	public String salvar(T entidate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(T entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T carregarEntidade(Projeto entidade) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> buscarTodos() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void consisteAntesEditar(T entidade) throws NegocioException {
		// TODO Auto-generated method stub
		
	}
}
