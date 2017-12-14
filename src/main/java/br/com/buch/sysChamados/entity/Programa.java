package br.com.buch.sysChamados.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CAD_PROGRAMA")
@PrimaryKeyJoinColumn(name="COD_CADPROJETO")
public class Programa extends Projeto implements Serializable{

	private static final long serialVersionUID = -157972869827725258L;

	
	@Column(name = "VERSAO", length = 20, nullable = false)
	private String versao;
	
	
	@Column(name = "MENU", length = 50, nullable = false)
	private String menu;
	
	
	@Column(name = "PROGRAMA_AUXILIAR", length = 1, nullable = false)
	private Character programaAuxiliar;
	
	//--------------------------------	GETs and SETs------------------------------//
	
	
	public String getVersao() {return versao;}	
	public void setVersao(String versao) {this.versao = versao;}
	
	
	public String getMenu() {return menu;}
	public void setMenu(String menu) {this.menu = menu;}
	
	
	public Character getProgramaAuxiliar() {return programaAuxiliar;}	
	public void setProgramaAuxiliar(Character programaAuxiliar) {this.programaAuxiliar = programaAuxiliar;}
}
