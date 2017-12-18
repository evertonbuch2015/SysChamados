package br.com.buch.sysChamados.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.buch.sysChamados.entity.Usuario;
import br.com.buch.sysChamados.service.UsuarioService;
import br.com.buch.sysChamados.util.NegocioException;
import br.com.buch.sysChamados.util.SessionContextUtil;
import br.com.buch.sysChamados.util.UtilMensagens;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

	private static final long serialVersionUID = 3718558851508169560L;

	private UsuarioService usuarioService;

	private String nomeUsuario;

	private String senha;

	private Map<String, String> themeColors;

	private String theme = "indigo";

	private String menuClass = null;

	private String profileMode = "inline";

	private String menuLayout = "static";

	private boolean compact = true;

	private Usuario usuarioLogado;

	// =======================METODOS DA CLASSE=====================================
	
	@PostConstruct
	public void init() {
		themeColors = new HashMap<String, String>();
		themeColors.put("indigo", "#3F51B5");
		themeColors.put("blue", "#03A9F4");
		themeColors.put("blue-grey", "#607D8B");
		themeColors.put("brown", "#795548");
		themeColors.put("cyan", "#00bcd4");
		themeColors.put("green", "#4CAF50");
		themeColors.put("purple-amber", "#673AB7");
		themeColors.put("purple-cyan", "#673AB7");
		themeColors.put("teal", "#009688");
		themeColors.put("dark-blue", "#3e464c");
		themeColors.put("dark-green", "#2f4050");
		
		usuarioService = new UsuarioService();  
	}

	
	public void efetuaLogin() throws Exception {
		
		try {
			this.usuarioLogado = usuarioService.fazerLogin(this.nomeUsuario, this.senha);
			
			if (usuarioLogado != null) {
				SessionContextUtil.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				SessionContextUtil.getInstance().redirecionar("/dashboard?faces-redirect=true");
				setConfiguracoesPerfil();
				// Constantes.getInstance().addUsuarioLogado(usuario);
			}
		}catch(NegocioException ex){
			this.usuarioLogado = null;
			UtilMensagens.mensagemAtencao(ex.getMessage());
		}catch (Exception e) {
			UtilMensagens.mensagemErro(e.getMessage());
		}
	}

	
	public void deslogar() {
		//Constantes.getInstance().removeUsuarioLogado(SessionContext.getInstance().getUsuarioLogado());
		SessionContextUtil.getInstance().deleteAttribute("usuarioLogado");		
		SessionContextUtil.getInstance().encerrarSessao();
	       
		SessionContextUtil.getInstance().redirecionar("/login?faces-redirect=true");
	}
	
	
	private void setConfiguracoesPerfil(){
		setCompact(true);
		setProfileMode(usuarioLogado.getProfile_mode());
		setTheme(usuarioLogado.getTheme());
		setMenuLayout(usuarioLogado.getMenuLayout());
		
		if(usuarioLogado.getMenuClass() == null || usuarioLogado.getMenuClass().equals("")){
			setLightMenu();
		}else{
			setDarkMenu();
		}
	}
	
	// =============================GET AND SET=====================================
		

	public String getMenuLayout() {
		if (this.menuLayout.equals("static"))
			return "menu-layout-static";
		else if (this.menuLayout.equals("overlay"))
			return "menu-layout-overlay";
		else if (this.menuLayout.equals("horizontal"))
			return "menu-layout-static menu-layout-horizontal";
		else
			return "menu-layout-static";
	}

	
	public Map<String, String> getThemeColors() {return this.themeColors;}
	
	
	public String getTheme() {return theme;}
	public void setTheme(String theme) {this.theme = theme;}

	
	public String getMenuClass() {return this.menuClass;}
		
	public void setLightMenu() {this.menuClass = null;}	
	
	public void setDarkMenu() {this.menuClass = "layout-menu-dark";}

	
	public String getProfileMode() {return this.profileMode;}
	public void setProfileMode(String profileMode) {this.profileMode = profileMode;}

	
	public void setMenuLayout(String menuLayout) {this.menuLayout = menuLayout;}

	
	public void setCompact(boolean value) {this.compact = value;}
	public boolean isCompact() {return this.compact;}

	
	public Usuario getUsuarioLogado() {return usuarioLogado;}
	public void setUsuarioLogado(Usuario usuarioLogado) {this.usuarioLogado = usuarioLogado;}

	
	public String getNomeUsuario() {return nomeUsuario;}
	public void setNomeUsuario(String nomeUsuario) {this.nomeUsuario = nomeUsuario;}

	
	public String getSenha() {return senha;}
	public void setSenha(String senha) {this.senha = senha;}

}
