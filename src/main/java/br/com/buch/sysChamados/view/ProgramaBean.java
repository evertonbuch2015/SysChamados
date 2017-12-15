package br.com.buch.sysChamados.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.buch.sysChamados.entity.Programa;
import br.com.buch.sysChamados.service.ProgramaService;


@ManagedBean
@ViewScoped
public class ProgramaBean extends GenericBean<Programa> implements Serializable{

	private static final long serialVersionUID = -8165871784161603162L;
	
	//Filtros
	
	
	public ProgramaBean() {
		super(new ProgramaService());	
	}
	
	
	// =======================METODOS DO USUARIO=====================================
	
	
	@Override
	public void filtrar() {

	}


	@Override
	public Programa criarEntidade() {
		return new Programa();
	}
	
	
	// =============================GET AND SET=====================================
	
	@Override
	public List<Programa> getEntidades() {	
		return super.getEntidades();
	}
}
