package br.com.buch.sysChamados.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.buch.sysChamados.entity.Usuario;
import br.com.buch.sysChamados.service.UsuarioService;


@ManagedBean
@ViewScoped
public class UsuarioBean extends GenericBean<Usuario, UsuarioService> implements Serializable{

	private static final long serialVersionUID = -8165871784161603162L;
	
	private List<String> setores;
	//Filtros
	
	
	public UsuarioBean() {
		super(new UsuarioService());	
	}
	
	
	// =======================METODOS DO USUARIO=====================================
	
	
	@Override
	public void filtrar() {

	}


	@Override
	public Usuario criarEntidade() {
		return new Usuario();
	}
	
	
	// =============================GET AND SET=====================================
	
	@Override
	public List<Usuario> getEntidades() {	
		return super.getEntidades();
	}

	
	public List<String> getSetores() throws Exception{
		if(this.setores == null){
			this.setores = service.buscarSetores();
		}
		return setores;
	}
}
