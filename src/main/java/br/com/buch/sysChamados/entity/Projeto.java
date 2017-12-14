package br.com.buch.sysChamados.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CAD_PROJETO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Projeto implements Serializable, BaseEntity{

	private static final long serialVersionUID = -8223068512412424345L;

	@Id
	@SequenceGenerator(name="G_CAD_PROJETO", sequenceName="\"G_CAD_PROJETO\"", allocationSize=1)  
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="G_CAD_PROJETO")
    @Column(name = "COD_CADPROJETO")
	private Long id;
	
	
	@Column(name = "CODIGO")
	private Integer codigo;
	
	@Column(name = "DESCRICAO", length = 100, nullable = false)
	private String descricao;
	
	
	//--------------------------------	GETs and SETs------------------------------//
	
	@Override
	public Long getId() {return id;}
	
	@Override
	public void setId(Long id) {this.id = id;}
	
	
	public Integer getCodigo() {return codigo;}	
	public void setCodigo(Integer codigo) {this.codigo = codigo;}
	
	
	public String getDescricao() {return descricao;}	
	public void setDescricao(String descricao) {this.descricao = descricao;}
	
}
