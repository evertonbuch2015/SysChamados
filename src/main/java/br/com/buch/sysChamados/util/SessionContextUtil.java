package br.com.buch.sysChamados.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.buch.sysChamados.entity.Usuario;

public class SessionContextUtil {
	private static SessionContextUtil instance;

	public static SessionContextUtil getInstance() {
		if (instance == null) {
			instance = new SessionContextUtil();
		}

		return instance;
	}

	private SessionContextUtil() {

	}

	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext n�o pode ser chamado fora de uma requisi��o HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}

	private FacesContext currentFacesContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext n�o pode ser chamado fora de uma requisi��o HTTP");
		} else {
			return FacesContext.getCurrentInstance();
		}
	}

	public void encerrarSessao() {
		currentExternalContext().invalidateSession();
	}

	public void redirecionar(String pagina) {
		NavigationHandler handler = currentFacesContext().getApplication().getNavigationHandler();
		handler.handleNavigation(currentFacesContext(), null, pagina);
	}

	public void adicionarMensagem(String message) {
		currentFacesContext().addMessage(null, new FacesMessage(message));
	}

	public Object getAttribute(String nome) {
		return currentExternalContext().getSessionMap().get(nome);
	}

	public void setAttribute(String nome, Object valor) {
		currentExternalContext().getSessionMap().put(nome, valor);
	}

	public void deleteAttribute(String key) {
		currentExternalContext().getSessionMap().remove(key);
	}

	public Usuario getUsuarioLogado() {
		Usuario usuarioLogado = (Usuario) currentExternalContext().getSessionMap().get("usuarioLogado");

		return usuarioLogado;
	}
}
