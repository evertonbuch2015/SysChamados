package br.com.buch.sysChamados.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author Everton
 */
@Entity
@Table(name = "SIS_USUARIO")
public class Usuario implements Serializable, BaseEntity {

	private static final long serialVersionUID = -2880609378828789038L;

	
	@Id
	@SequenceGenerator(name = "G_SIS_USUARIO", sequenceName = "\"G_SIS_USUARIO\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_SIS_USUARIO")
	@Column(name = "COD_SISUSUARIO")
	private Long id;

	
	@NotEmpty(message = "O Usuário deve ser informado!")
	@Column(name = "NOME_USUARIO", nullable = true, length = 20)
	private String nomeUsuario;

	@Column(name = "SENHA", nullable = true, length = 70)
	private String senha;
	
	
	@Column(name = "NOME_COLABORADOR", nullable = false, length = 40)
	private String nomeColaborador;
		
	@Column(name="EMAIL" , nullable = false, length = 60)
	private String email;	
	
	@Column(name="FRASE_SECRETA" , nullable = false, length = 60)
	private String fraseSecreta;

	@Column(name = "GRUPO_USUARIO", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private GrupoUsuario grupo;
	
	@NotEmpty(message = "O Setor deve ser informado!")
	@Column(name = "SETOR", length = 50)
	private String setor;

	@Column(name = "ATIVO")
	private Character ativo;

	@Column(name = "EM_FERIAS")
	private Character emFerias;

	
	@Column(name = "THEME", length = 20)
	private String theme;			// 
	
		
	@Column(name = "MENU_CLASS", length = 20)
	private String menuClass;		//null or layout-menu-dark
	
		
	@Column(name = "MENU_LAYOUT", length = 20)
	private String menuLayout;		//overlay, horizontal or static
	
	
	@Column(name = "PROFILE_MODE", length = 20)
	private String profile_mode;	//inline or overlay
	
	
	// -------------------------------- GETs and SETs------------------------------//

	@Override
	public Long getId() {return id;}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomeColaborador() {return nomeColaborador;}
	public void setNomeColaborador(String nomeColaborador) {this.nomeColaborador = nomeColaborador;}

	
	public String getNomeUsuario() {return nomeUsuario;}
	public void setNomeUsuario(String nomeUsuario) {this.nomeUsuario = nomeUsuario;}

	
	public GrupoUsuario getGrupo() {return grupo;}
	public void setGrupo(GrupoUsuario grupo) {this.grupo = grupo;}

	
	public String getSetor() {return setor;}

	public void setSetor(String setor) {this.setor = setor;}

	
	public Character getAtivo() {return ativo;}
	
	public void setAtivo(Character ativo) {this.ativo = ativo;}
	
	
	public Character getEmFerias() {return emFerias;}

	public void setEmFerias(Character emFerias) {this.emFerias = emFerias;}

	
	public String getSenha() {return senha;}

	public void setSenha(String senha) {this.senha = senha;}
	
	
	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}


	public String getFraseSecreta() {return fraseSecreta;}

	public void setFraseSecreta(String fraseSecreta) {this.fraseSecreta = fraseSecreta;}
	
	

	public Boolean isAtivo() {
		if (this.ativo == null)
			return null;
		
		return ativo.equals('S') ? true : false;
	}

	
	public String getTheme() {return theme;}
	public void setTheme(String theme) {this.theme = theme;}

	
	public String getMenuClass() {return menuClass;}
	public void setMenuClass(String menuClass) {this.menuClass = menuClass;}

	
	public String getMenuLayout() {return menuLayout;}
	public void setMenuLayout(String menuLayout) {this.menuLayout = menuLayout;}

	
	public String getProfile_mode() {return profile_mode;}
	public void setProfile_mode(String profile_mode) {this.profile_mode = profile_mode;}

	
	// -------------------------------- Métodos Auxiliares------------------------------//
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}