package br.com.buch.sysChamados.service;

import java.util.List;

import br.com.buch.sysChamados.entity.BaseEntity;
import br.com.buch.sysChamados.util.NegocioException;
import br.com.buch.sysChamados.util.PersistenciaException;

public interface GenericService<T extends BaseEntity>{

	
	public String salvar(T entidate)throws Exception;
		
	public String excluir(T entidade)throws Exception;
	
	public T carregarEntidade(T entidade)throws PersistenciaException;
	
	public List<T> buscarTodos()throws Exception;
	
	public void consisteAntesEditar(T entidade)throws NegocioException;
}
