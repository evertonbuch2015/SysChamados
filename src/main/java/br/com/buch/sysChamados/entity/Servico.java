package br.com.buch.sysChamados.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CAD_SERVICO")
@PrimaryKeyJoinColumn(name="COD_CADPROJETO")
public class Servico extends Projeto implements Serializable{

	private static final long serialVersionUID = -7748431529190618591L;
	
}
