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

	
	public GrupoUsuario getGrupoUsuario() {return grupo;}
	public void setGrupoUsuario(GrupoUsuario grupo) {this.grupo = grupo;}

	
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
	
	// -------------------------------- Métodos Auxiliares------------------------------//

	
	
}