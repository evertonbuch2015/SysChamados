package br.com.buch.sysChamados.view;

import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.buch.sysChamados.entity.BaseEntity;
import br.com.buch.sysChamados.service.GenericService;
import br.com.buch.sysChamados.util.NegocioException;
import br.com.buch.sysChamados.util.UtilMensagens;


public abstract class GenericBean<T extends BaseEntity> {
	
	public enum EstadoTela {INSERINDO, ALTERANDO, BUSCANDO, VISUALIZANDO}	
	private EstadoTela estadoTela = EstadoTela.BUSCANDO;
	
	protected T entidade;
	protected List<T> entidades;
	protected GenericService<T> service;
	
	public GenericBean(GenericService<T> service) {
		this.service = service;
	}
	
	// ================Métodos para controlar e consultar o estado da Tela.===========
	
	public boolean isInserindo()    {return estadoTela.equals(EstadoTela.INSERINDO);}

	public boolean isAlterando()    {return estadoTela.equals(EstadoTela.ALTERANDO);}

	public boolean isBuscando()     {return estadoTela.equals(EstadoTela.BUSCANDO);}
	
	public boolean isVisualizando() {return estadoTela.equals(EstadoTela.VISUALIZANDO);}

	
	public void mudarInserir()      {this.estadoTela = EstadoTela.INSERINDO;}

	public void mudarAlterar()      {this.estadoTela = EstadoTela.ALTERANDO;}

	public void mudarBuscar()       {this.estadoTela = EstadoTela.BUSCANDO;}
	
	public void mudarVisualizar()   {this.estadoTela = EstadoTela.VISUALIZANDO;}
	
		
	// ================Metodos a serem Implementados==================================
	
	public abstract void filtrar();
	
	
	public abstract T criarEntidade();
	
	
	// ================Metodos já implementados (Prontos)=============================
	
	public void gravar(){		
		try {
			String mensagem = service.salvar(this.entidade);
			refresh();
			mudarBuscar();
			
			UtilMensagens.mensagemInformacao(mensagem);
		}
		catch (NegocioException e) {
			UtilMensagens.mensagemAtencao(e.getMessage());
		}
		catch (Exception e) {
			UtilMensagens.mensagemErro(e.getMessage());
		}
	}
	
	
	public void excluir(){
		try {
			service.excluir(entidade);
			refresh();
			mudarBuscar();
			
			UtilMensagens.mensagemInformacao("Exclusão Realizada com Sucesso");
		}
		catch (NegocioException e) {
			UtilMensagens.mensagemAtencao(e.getMessage());
		}
		catch (Exception e) {
			UtilMensagens.mensagemErro(e.getMessage());
		}
		
	}
	
	
	public void excluir(T entity){
		try {
			service.excluir(entity);			
			refresh();
			mudarBuscar();
			
			UtilMensagens.mensagemInformacao("Exclusão Realizada com Sucesso");
		}
		catch (NegocioException e) {
			UtilMensagens.mensagemAtencao(e.getMessage());
		}
		catch (Exception e) {
			UtilMensagens.mensagemErro(e.getMessage());
		}
	}
		
	
	public  void refresh(){				
		try {			
			if(this.entidades != null){
				this.entidades.clear();
			}
			this.entidades = service.buscarTodos();
			
		} catch (Exception e) {
			UtilMensagens.mensagemErro(e.getMessage());
		}
	}

	
	public void novo(){
		this.entidade = criarEntidade();
		mudarInserir();
	}
	
	
	public void editar(){
		try {
			service.consisteAntesEditar(this.entidade);
			service.carregarEntidade(this.entidade);		
			mudarAlterar();			
		}
		catch (NegocioException ex) {
			UtilMensagens.mensagemAtencao(ex.getMessage());
		}
		catch (Exception e) {
			UtilMensagens.mensagemErro(e.getMessage());
		}
	}
	
		
	public void editar(T entidade){					
		try {
			service.consisteAntesEditar(entidade);			
			service.carregarEntidade(entidade);		
			mudarAlterar();			
		}
		catch (NegocioException ex) {
			UtilMensagens.mensagemAtencao(ex.getMessage());
		}
		catch (Exception e) {
			UtilMensagens.mensagemErro(e.getMessage());
		}
	}
	

	public void cancelar(){
		this.entidade = null;
		mudarBuscar();
	}
	
	
	public void visualizar(){
		try {			
			if(this.entidade != null){
				service.carregarEntidade(entidade);
				mudarVisualizar();
			}else{
				UtilMensagens.mensagemInformacao("Selecione um registro da lista!");
			}
			
		} catch (Exception e) {
			UtilMensagens.mensagemErro(e.getMessage());
		}		
	}
	
			
	@SuppressWarnings("unchecked")
	public void onRowSelect(SelectEvent event) {this.entidade = (T) event.getObject();}	
	
	
	public void onRowDblClckSelect(final SelectEvent event) {visualizar();}
	

	// ================Métodos GET e SET=============================================
	
	public void setEntidade(T entidade) {this.entidade = entidade;}
	
	public T getEntidade() {
		if(this.entidade == null){
			this.entidade = criarEntidade();
		}
		return (T) entidade;
	}
	
	
	public void setEntidades(List<T> entidades) {this.entidades = entidades;}
	
	public List<T> getEntidades() {
		if (this.entidades == null) {
			refresh();
		}	
		return (List<T>) entidades;
	}
}
